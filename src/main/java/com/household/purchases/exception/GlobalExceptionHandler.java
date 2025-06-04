package com.household.purchases.exception;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * Handles custom {@link DuplicateResourceException} when a resource with the same unique field already exists.
     * This can be triggered by trying to save a resource (e.g., Ingredient, Dish, Menu) with a non-unique name or identifier.
     *
     * @param ex the thrown DuplicateResourceException
     * @return HTTP 409 Conflict response with error details
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
        log.warn("Duplicate resource error: {}", ex.getMessage());
        return generateErrorResponse(HttpStatus.CONFLICT, ex.getLocalizedMessage());
    }

    /**
     * Handles {@link InvalidEnumCodeException} which is thrown when a provided string value
     * does not match any of the expected values in an enumeration.
     * <p>
     * This typically occurs when an invalid unit of measurement or similar value is submitted
     * in a request body.
     *
     * @param ex the thrown InvalidEnumCodeException
     * @return HTTP 400 Bad Request response with details about the invalid enum value
     */
    @ExceptionHandler(InvalidEnumCodeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnumCodeException(InvalidEnumCodeException ex) {
        log.warn("Invalid enum code: {}", ex.getMessage());
        return generateErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
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
                new ErrorResponse(status, errorText, status.name()));
    }

    /**
     * Structure for returning standardized error responses to API clients.
     *
     * @param status     HTTP status of the error
     * @param message    general message about the error
     * @param errorCode  unique identifier for the error type
     */
    public record ErrorResponse(
            HttpStatus status,
            String message,
            String errorCode
    ) {}
}
