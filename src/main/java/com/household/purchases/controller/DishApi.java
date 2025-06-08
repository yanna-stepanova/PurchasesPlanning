package com.household.purchases.controller;

import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
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

import java.util.List;

/**
 * REST API for managing dishes and dish ingredients.
 */
@Tag(name = "Dish API", description = "Operations related to dishes")
public interface DishApi {

    /**
     * Get a paginated list of dishes.
     *
     * @param pageable pagination parameters
     * @return a page of dishes
     */
    @Operation(summary = "Get paginated list of dishes")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dishes",
            content = @Content(schema = @Schema(implementation = DishShortDto.class)))
    @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    ResponseEntity<Page<DishShortDto>> getAllDishes(@ParameterObject Pageable pageable);

    /**
     * Get dish by ID.
     *
     * @param id dish ID
     * @return dish details
     */
    @Operation(summary = "Get dish by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dish")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied")
    ResponseEntity<DishDto> getDishById(@PathVariable @Positive Long id);

    /**
     * Create a new dish.
     *
     * @param dto dish data
     * @return created dish
     */
    @Operation(summary = "Create a new dish")
    @ApiResponse(responseCode = "200", description = "Successfully created dish")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    @ApiResponse(responseCode = "409", description = "Dish with the given name already exists")
    ResponseEntity<DishDto> createDish(@RequestBody @Valid CreateDishDto dto);

    /**
     * Update dish by ID.
     *
     * @param id dish ID
     * @param dto updated dish data
     * @return updated dish
     */
    @Operation(summary = "Update dish by ID")
    @ApiResponse(responseCode = "200", description = "Successfully updated dish")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    ResponseEntity<DishDto> updateDish(@PathVariable @Positive Long id,
                                       @RequestBody @Valid UpdateDishDto dto);

    /**
     * Delete dish by ID.
     *
     * @param id dish ID
     * @return no content
     */
    @Operation(summary = "Delete dish by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted dish")
    @ApiResponse(responseCode = "400", description = "Invalid ID")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    ResponseEntity<Void> deleteDish(@PathVariable @Positive Long id);

    //----------------------------------------------------------------------------------------------------
    //   DishIngredient Api
    //----------------------------------------------------------------------------------------------------
    @Operation(summary = "Create a new dish ingredient")
    @ApiResponse(responseCode = "200", description = "Successfully created dish ingredient")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    ResponseEntity<DishIngredientDto> createDishIngredient(@PathVariable @Positive Long dishId,
                                                           @RequestBody @Valid CreateDishIngredientDto dto);

    /**
     * Update an existing ingredient for a specific dish.
     *
     * @param dishId ID of the dish to update
     * @param ingredientId ID of the ingredient to update in the dish
     * @param dto updated data for the dish ingredient
     * @return updated dish with the modified ingredient
     */
    @Operation(summary = "Update ingredient of a dish")
    @ApiResponse(responseCode = "200", description = "Successfully updated ingredient in the dish")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Dish or ingredient not found")
    ResponseEntity<DishIngredientDto> updateDishIngredient(@PathVariable @Positive Long dishId,
                                                       @PathVariable @Positive Long ingredientId,
                                                       @RequestBody @Valid UpdateDishIngredientDto dto);

    /**
     * Delete ingredient of dish by id
     *
     * @param dishId ID of the dish
     * @param dishIngredientId ID row
     * @return ID of the ingredient
     */
    @Operation(summary = "Delete ingredient from dish by ID")
    @ApiResponse(responseCode = "204", description = "Successfully deleted ingredient from dish")
    @ApiResponse(responseCode = "400", description = "Invalid ID")
    @ApiResponse(responseCode = "404", description = "Dish or ingredient not found")
    ResponseEntity<Void> deleteDishIngredient(@PathVariable @Positive Long dishId,
                                              @PathVariable @Positive Long dishIngredientId);

    /**
     * get all dish ingredients
     *
     * @param dishId dishId
     * @return {@link ResponseEntity}
     * @see ResponseEntity
     * @see List
     */
    @Operation(summary = "Get all ingredients of a dish")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dish ingredients",
            content = @Content(schema = @Schema(implementation = DishIngredientShortDto.class)))
    @ApiResponse(responseCode = "404", description = "Dish not found")
    ResponseEntity<List<DishIngredientShortDto>> getAllDishIngredient(@PathVariable @Positive Long dishId);
}
