package com.household.purchases.dto.dishingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishIngredientDto {
    private Long id;
    private Long dishId;
    private String dishName;
    private Long ingredientId;
    private String ingredientName;
    private Double quantity;
    private String unit;
}
