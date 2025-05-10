package com.household.purchases.dto.ingredient;

import jakarta.validation.constraints.PositiveOrZero;

public record IngredientRequest(String name,
                                String unit,
                                @PositiveOrZero Double pricePerUnit) {}
