package com.household.purchases.dto.dishingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UpdateDishIngredientDto(
        @Positive Double quantity,
        @NotBlank String unit) {}
