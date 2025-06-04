package com.household.purchases.dto.dish;

import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.enums.WeatherType;

import java.util.List;

public record UpdateDishDto(String name,
                            String description,
                            WeatherType weatherType,
                            List<CreateDishIngredientDto> ingredients) {}
