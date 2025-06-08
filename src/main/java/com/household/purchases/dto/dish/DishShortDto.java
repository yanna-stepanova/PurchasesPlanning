package com.household.purchases.dto.dish;

import com.household.purchases.enums.WeatherType;

public record DishShortDto(Long id,
                           String name,
                           WeatherType weatherType) {}
