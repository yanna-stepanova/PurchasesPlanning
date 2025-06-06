package com.household.purchases.service;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.model.Ingredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    /**
     * get entity by id
     *
     * @param id id
     * @return {@link Ingredient}
     * @see Ingredient
     */
    Ingredient getEntityById(@Positive @NotBlank Long id);
}
