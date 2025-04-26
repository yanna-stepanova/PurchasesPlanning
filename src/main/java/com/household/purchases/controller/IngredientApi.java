package com.household.purchases.controller;

import com.household.purchases.dto.ingredient.IngredientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * REST API for managing ingredients.
 */
@Tag(name = "Ingredient", description = "Operations related to ingredients")
public interface IngredientApi {

    /**
     * Get a paginated list of ingredients.
     *
     * @param pageable pagination parameters
     * @return a page of ingredients
     */
    @Operation(summary = "Get paginated list of ingredients")
    ResponseEntity<Page<IngredientDto>> getAll(@ParameterObject Pageable pageable);
}
