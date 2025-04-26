package com.household.purchases.mapper;

import com.household.purchases.config.MapperConfiguration;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.model.Ingredient;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface IngredientMapper {

    IngredientDto toDto(Ingredient ingredient);
}
