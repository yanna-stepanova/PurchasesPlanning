package com.household.purchases.service;

import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.model.Dish;
import com.household.purchases.model.DishIngredient;
import com.household.purchases.model.Ingredient;

public interface DishIngredientService {

    DishIngredient create(Dish dish, Ingredient ingredient, CreateDishIngredientDto dto);

    DishIngredient getByDishAndIngredient(Long dishId, Long ingredientId);

    DishIngredientDto update(Long dishId, Long ingredientId, UpdateDishIngredientDto dto);
    
    DishIngredientDto toDto(DishIngredient dishIngredient);

    void delete(Long dishId, Long dishIngredientId);
}
