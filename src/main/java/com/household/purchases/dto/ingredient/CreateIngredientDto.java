package com.household.purchases.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateIngredientDto(@NotBlank String name,
                                  @NotBlank String unit,
                                  @PositiveOrZero Double pricePerUnit) {}
