package com.household.purchases.mapper;

import com.household.purchases.config.MapperConfiguration;
import com.household.purchases.dto.dish.CreateDishDto;
import com.household.purchases.dto.dish.DishDto;
import com.household.purchases.dto.dish.DishShortDto;
import com.household.purchases.dto.dish.UpdateDishDto;
import com.household.purchases.model.Dish;
import com.household.purchases.model.DishIngredient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfiguration.class, uses = {DishIngredientMapper.class})
public interface DishMapper {

    @Mapping(target = "ingredients", ignore = true)
    Dish toModel(CreateDishDto dto);

    DishDto toDto(Dish dish);

    DishShortDto toShortDto(Dish dish);

    @Mapping(target = "ingredients", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Dish patchFromDto(@MappingTarget Dish dish, UpdateDishDto dto);

    DishIngredient updateFromDto(DishIngredient dishIngredient, UpdateDishDto dto);
}
