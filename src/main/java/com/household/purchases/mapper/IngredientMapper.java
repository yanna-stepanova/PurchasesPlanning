package com.household.purchases.mapper;

import com.household.purchases.config.MapperConfiguration;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.IngredientRequest;
import com.household.purchases.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface IngredientMapper {

    Ingredient toModel(IngredientRequest request);

    IngredientDto toDto(Ingredient ingredient);

    Ingredient updateFromDto(@MappingTarget Ingredient ingredient, IngredientRequest request);
}
