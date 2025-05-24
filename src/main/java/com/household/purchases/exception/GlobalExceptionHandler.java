package com.household.purchases.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Global exception handler that provides centralized exception handling across all {@code @RestController} components.
 * It transforms exceptions into structured error responses for the client.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles custom {@link NotFoundException} when a requested resource is not found.
     *
     * @param ex the thrown NotFoundException
     * @return HTTP 404 Not Found response with error details
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        log.warn("Not found error: {}", ex.getMessage());
        return generateErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    /**
     * Handles {@link IllegalArgumentException} thrown on invalid business logic inputs.
     *
     * @param ex the thrown IllegalArgumentException
     * @return HTTP 400 Bad Request response with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Validation error: {}", ex.getMessage());
        return generateErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * Handles general unexpected exceptions.
     *
     * @param ex the thrown Exception
     * @return HTTP 500 Internal Server Error response with generic message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Unexpected error: ", ex);
        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String rootMessage = Optional.ofNullable(ex.getRootCause())
                .map(Throwable::getMessage)
                .orElse(ex.getMessage());
        log.warn("Data integrity violation: {}", rootMessage);
        return generateErrorResponse(HttpStatus.CONFLICT, rootMessage);
    }

    /**
     * Handles validation errors for invalid request bodies.
     *
     * @param ex the MethodArgumentNotValidException
     * @param headers HTTP headers
     * @param status HTTP status code
     * @param request Web request
     * @return HTTP 400 Bad Request with validation error messages
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.warn("Validation error: {}", ex.getMessage());
        Map<String,Object> bodyErrors = new LinkedHashMap<>();
        bodyErrors.put("timestamp", LocalDateTime.now());
        bodyErrors.put("status", HttpStatus.BAD_REQUEST);
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        bodyErrors.put("errors", errors);
        return new ResponseEntity<>(bodyErrors, headers, status);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String field = fieldError.getField();
            String message = error.getDefaultMessage();
            return field + " " + message;
        }
        return error.getDefaultMessage();
    }

    private ResponseEntity<ErrorResponse> generateErrorResponse(HttpStatus status, String errorText) {
        return ResponseEntity.status(status).body(
                new ErrorResponse(status, errorText, List.of(), status.name()));
    }

    /**
     * Structure for returning standardized error responses to API clients.
     *
     * @param status     HTTP status of the error
     * @param message    general message about the error
     * @param errors     list of specific error details, such as field validation errors
     * @param errorCode  unique identifier for the error type
     */
    public record ErrorResponse(
            HttpStatus status,
            String message,
            List<String> errors,
            String errorCode
    ) {}
}
