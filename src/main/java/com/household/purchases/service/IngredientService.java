package com.household.purchases.service;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing ingredients.
 */
public interface IngredientService {
    /**
     * Get a paginated list of ingredients.
     *
     * @param pageable pagination parameters
     * @return a page of ingredients
     */
    Page<IngredientDto> getAll(Pageable pageable);

    /**
     * Get ingredient by ID.
     *
     * @param id ingredient ID
     * @return ingredient details
     */
    IngredientDto getById(Long id);

    /**
     * Create a new ingredient.
     *
     * @param dto ingredient data
     * @return created ingredient
     */
    IngredientDto create(CreateIngredientDto dto);

    /**
     * Update ingredient by ID.
     *
     * @param id ingredient ID
     * @param dto updated ingredient data
     * @return updated ingredient
     */
    IngredientDto update(Long id, UpdateIngredientDto dto);

    /**
     * Delete ingredient by ID.
     *
     * @param id ingredient ID
     */
    void delete(Long id);
}
