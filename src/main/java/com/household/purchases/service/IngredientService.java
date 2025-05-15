package com.household.purchases.service;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;

/**
 * Service interface for managing ingredients.
 */
public interface IngredientService extends BaseService<IngredientDto, CreateIngredientDto, UpdateIngredientDto> {
}
