package com.household.purchases.service;

import com.household.purchases.dto.ingredient.IngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service for managing ingredients.
 */
public interface IngredientService {
    /**
     * Returns a paginated list of ingredients.
     *
     * @param pageable pagination parameters
     * @return page of ingredients
     */
    Page<IngredientDto> getAll(Pageable pageable);
}
