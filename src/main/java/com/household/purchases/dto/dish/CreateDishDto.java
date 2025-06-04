package com.household.purchases.dto.dish;

import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.enums.WeatherType;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateDishDto(@NotBlank String name,
                            String description,
                            WeatherType weatherType,
                            List<CreateDishIngredientDto> ingredients) {}
