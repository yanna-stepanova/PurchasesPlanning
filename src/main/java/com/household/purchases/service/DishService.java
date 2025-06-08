package com.household.purchases.service;

import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing dishes.
 */
public interface DishService {
    /**
     * Get a paginated list of dishes.
     *
     * @param pageable pagination parameters
     * @return a page of dishes
     */
    Page<DishShortDto> getAllDishes(Pageable pageable);

    /**
     * Get dish by ID.
     *
     * @param id dish ID
     * @return dish details
     */
    DishDto getDishById(Long id);

    /**
     * Create a new dish.
     *
     * @param dto dish data
     * @return created dish
     */
    DishDto createDish(CreateDishDto dto);

    /**
     * Update dish by ID.
     *
     * @param id dish ID
     * @param dto updated dish data
     * @return updated dish
     */
    DishDto updateDish(Long id, UpdateDishDto dto);

    /**
     * Delete dish by ID.
     *
     * @param id dish ID
     */
    void deleteDish(Long id);

    /**
     * Update an ingredient of a specific dish.
     *
     * @param dishId ID of the dish
     * @param ingredientId ID of the ingredient in the dish
     * @param dto updated ingredient data
     * @return updated ingredient
     */
    DishIngredientDto updateIngredient(Long dishId,
                                       Long ingredientId,
                                       UpdateDishIngredientDto dto);

    /**
     * Create a new ingredient.
     *
     * @param dishId dishId
     * @param dto dto
     * @return {@link DishIngredientDto}
     * @see DishIngredientDto
     */
    DishIngredientDto createIngredient(Long dishId, CreateDishIngredientDto dto);

    /**
     * Delete dishIngredient by ID.
     */
    void deleteIngredient(Long dishId, Long dishIngredientId);

    /**
     * Get all ingredients by dish
     *
     * @param dishId dishId
     * @return {@link List}
     * @see List
     * @see DishIngredientShortDto
     */
    List<DishIngredientShortDto> getAllIngredientsByDish(Long dishId);
}
