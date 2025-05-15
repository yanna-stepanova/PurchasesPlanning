package com.household.purchases.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Generic REST API interface for managing entities.
 *
 * @param <T>         DTO for output
 * @param <CreateDto> DTO for creating entity
 * @param <UpdateDto> DTO for updating entity
 */
@Tag(name = "Base API", description = "Generic CRUD operations")
public interface BaseApi<T, CreateDto, UpdateDto> {

    /**
     * Get a paginated list of entities.
     *
     * @param pageable pagination parameters
     * @return a page of entities
     */
    @Operation(summary = "Get paginated list of entities")
    ResponseEntity<Page<T>> getAll(@ParameterObject Pageable pageable);

    /**
     * Get entity by ID.
     *
     * @param id entity ID
     * @return entity details
     */
    @Operation(summary = "Get entity by ID")
    ResponseEntity<T> getById(@PathVariable @Positive Long id);

    /**
     * Create a new entity.
     *
     * @param dto entity data
     * @return created entity
     */
    @Operation(summary = "Create a new entity")
    ResponseEntity<T> create(@RequestBody @Valid CreateDto dto);

    /**
     * Update entity by ID.
     *
     * @param id      entity ID
     * @param dto updated entity data
     * @return updated entity
     */
    @Operation(summary = "Update entity by ID")
    ResponseEntity<T> update(@PathVariable @Positive Long id, @RequestBody @Valid UpdateDto dto);

    /**
     * Delete entity by ID.
     *
     * @param id entity ID
     * @return no content
     */
    @Operation(summary = "Delete entity by ID")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id);
}
