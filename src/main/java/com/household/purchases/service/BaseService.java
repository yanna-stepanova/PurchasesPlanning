package com.household.purchases.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic service interface for CRUD operations.
 *
 * @param <T>         DTO for output
 * @param <CreateDto> DTO for creating entity
 * @param <UpdateDto> DTO for updating entity
 */
public interface BaseService<T, CreateDto, UpdateDto> {

    /**
     * Get a paginated list of entities.
     *
     * @param pageable pagination parameters
     * @return a page of entities
     */
    Page<T> getAll(Pageable pageable);

    /**
     * Get entity by ID.
     *
     * @param id entity ID
     * @return entity details
     */
    T getById(Long id);

    /**
     * Create a new entity.
     *
     * @param dto entity data
     * @return created entity
     */
    T create(CreateDto dto);

    /**
     * Update entity by ID.
     *
     * @param id      entity ID
     * @param dto updated entity data
     * @return updated entity
     */
    T update(Long id, UpdateDto dto);

    /**
     * Delete entity by ID.
     *
     * @param id entity ID
     */
    void delete(Long id);
}
