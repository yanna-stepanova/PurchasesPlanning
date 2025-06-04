package com.household.purchases.service;

import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    Page<DishShortDto> getAll(Pageable pageable);

    /**
     * Get dish by ID.
     *
     * @param id dish ID
     * @return dish details
     */
    DishDto getById(Long id);

    /**
     * Create a new dish.
     *
     * @param dto dish data
     * @return created dish
     */
    DishDto create(CreateDishDto dto);

    /**
     * Update dish by ID.
     *
     * @param id dish ID
     * @param dto updated dish data
     * @return updated dish
     */
    DishDto update(Long id, UpdateDishDto dto);

    /**
     * Delete dish by ID.
     *
     * @param id dish ID
     */
    void delete(Long id);

    DishIngredientDto updateIngredient(@Positive Long dishId,
                                       @Positive Long ingredientId,
                                       @Valid UpdateDishIngredientDto dto);

    DishIngredientDto createIngredient(@Positive Long dishId, @Valid CreateDishIngredientDto dto);

    /**
     * Delete dishIngredient by ID.
     */
    void deleteIngredient(@Positive Long dishId, @Positive Long dishIngredientId);
}
