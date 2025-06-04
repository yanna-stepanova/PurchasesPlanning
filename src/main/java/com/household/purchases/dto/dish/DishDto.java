package com.household.purchases.dto.dish;

import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.enums.WeatherType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishDto {
    private Long id;
    private String name;
    private String description;
    private WeatherType weatherType;
    private List<DishIngredientShortDto> ingredients;
}
