package com.household.purchases.dto.dishingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDishIngredientDto(
        @Positive @NotNull Long ingredientId,
        @Positive Double quantity,
        @NotBlank String unit) {}
