package com.household.purchases.mapper;

import com.household.purchases.config.MapperConfiguration;
import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface IngredientMapper {

    Ingredient toModel(CreateIngredientDto dto);

    IngredientDto toDto(Ingredient ingredient);

    Ingredient updateFromDto(@MappingTarget Ingredient ingredient, UpdateIngredientDto dto);
}
