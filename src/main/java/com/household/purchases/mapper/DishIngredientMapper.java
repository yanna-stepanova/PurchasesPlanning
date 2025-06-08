package com.household.purchases.mapper;

import com.household.purchases.config.MapperConfiguration;
import com.household.purchases.dto.dishingredient.CreateDishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientDto;
import com.household.purchases.dto.dishingredient.DishIngredientShortDto;
import com.household.purchases.dto.dishingredient.UpdateDishIngredientDto;
import com.household.purchases.enums.MeasurementType;
import com.household.purchases.model.DishIngredient;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfiguration.class, uses = {IngredientMapper.class, DishMapper.class})
public interface DishIngredientMapper {

    @Mapping(target = "unit", ignore = true)
    DishIngredient toModel(CreateDishIngredientDto dto);

    @Mapping(target = "ingredientId", source = "ingredient.id")
    @Mapping(target = "name", source = "ingredient.name")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "mapUnitToString")
    DishIngredientShortDto toShortDto(DishIngredient dishIngredient);

    @Mapping(target = "dishId", source = "dish.id")
    @Mapping(target = "dishName", source = "dish.name")
    @Mapping(target = "ingredientId", source = "ingredient.id")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    @Mapping(target = "unit", source = "unit", qualifiedByName = "mapUnitToString")
    DishIngredientDto toDto(DishIngredient dishIngredient);

    @Mapping(target = "unit", ignore = true)
    DishIngredient updateFromDto(@MappingTarget DishIngredient entity, UpdateDishIngredientDto dto);

    @Named("mapUnitToString")
    static String mapUnitToString(MeasurementType type) {
        return type != null ? type.getCodeName() : null;
    }

    @AfterMapping
    default void setUnit(@MappingTarget DishIngredient entity, CreateDishIngredientDto dto) {
        String unitName = dto.unit();
        if (unitName != null && !unitName.isBlank()) {
            entity.setUnit(MeasurementType.getCode(unitName));
        }
    }

    @AfterMapping
    default void setUnit(@MappingTarget DishIngredient entity, UpdateDishIngredientDto dto) {
        String unitName = dto.unit();
        if (unitName != null && !unitName.isBlank()) {
            entity.setUnit(MeasurementType.getCode(unitName));
        }
    }
}
