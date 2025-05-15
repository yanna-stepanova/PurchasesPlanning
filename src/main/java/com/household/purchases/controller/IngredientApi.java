package com.household.purchases.controller;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST API for managing ingredients.
 */
@Tag(name = "Ingredient", description = "Operations related to ingredients")
public interface IngredientApi extends BaseApi<IngredientDto, CreateIngredientDto, UpdateIngredientDto> {
}
