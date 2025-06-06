package com.household.purchases.dto.dishingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishIngredientShortDto {
    private Long id;
    private Long ingredientId;
    private String name;
    private Double quantity;
    private String unit;
}
