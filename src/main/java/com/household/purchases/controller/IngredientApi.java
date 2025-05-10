package com.household.purchases.controller;

import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.IngredientRequest;
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

    @Operation(summary = "Get ingredient by ID")
    ResponseEntity<IngredientDto> getById(@PathVariable @Positive Long id);

    @Operation(summary = "Create a new ingredient")
    ResponseEntity<IngredientDto> create(@RequestBody @Valid IngredientRequest request);

    @Operation(summary = "Update an ingredient by ID")
    ResponseEntity<IngredientDto> update(@PathVariable @Positive Long id, @RequestBody @Valid IngredientRequest request);

    @Operation(summary = "Delete an ingredient by ID")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id);
}
