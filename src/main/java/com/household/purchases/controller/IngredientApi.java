package com.household.purchases.controller;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "Ingredient API", description = "Operations related to ingredients")
public interface IngredientApi {

    /**
     * Get a paginated list of ingredients.
     *
     * @param pageable pagination parameters
     * @return a page of ingredients
     */
    @Operation(summary = "Get paginated list of ingredients")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved ingredients",
            content = @Content(schema = @Schema(implementation = Page.class)))
    @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    ResponseEntity<Page<IngredientDto>> getAll(@ParameterObject Pageable pageable);

    /**
     * Get ingredient by ID.
     *
     * @param id ingredient ID
     * @return ingredient details
     */
    @Operation(summary = "Get ingredient by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved ingredient")
    @ApiResponse(responseCode = "404", description = "Ingredient not found")
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
    ResponseEntity<IngredientDto> getById(@PathVariable @Positive Long id);

    /**
     * Create a new ingredient.
     *
     * @param dto ingredient data
     * @return created ingredient
     */
    @Operation(summary = "Create a new ingredient")
    @ApiResponse(responseCode = "200", description = "Successfully created ingredient")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    ResponseEntity<IngredientDto> create(@RequestBody @Valid CreateIngredientDto dto);

    /**
     * Update ingredient by ID.
     *
     * @param id ingredient ID
     * @param dto updated ingredient data
     * @return updated ingredient
     */
    @Operation(summary = "Update ingredient by ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated ingredient")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Ingredient not found")
    ResponseEntity<IngredientDto> update(@PathVariable @Positive Long id,
                                         @RequestBody @Valid UpdateIngredientDto dto);

    /**
     * Delete ingredient by ID.
     *
     * @param id ingredient ID
     * @return no content
     */
    @Operation(summary = "Delete ingredient by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted ingredient")
    @ApiResponse(responseCode = "400", description = "Invalid ID")
    @ApiResponse(responseCode = "404", description = "Ingredient not found")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id);
}
