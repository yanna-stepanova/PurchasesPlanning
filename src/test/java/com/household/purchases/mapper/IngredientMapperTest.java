package com.household.purchases.mapper;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.model.Ingredient;
import com.household.purchases.util.DataFactoryForMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("IngredientMapper unit tests")
class IngredientMapperTest {
    private IngredientMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(IngredientMapper.class);
    }

    @Test
    @DisplayName("Convert the CreateIngredientDto to Ingredient entity correctly")
    void toModel_WithValidCreateIngredientDto_Success() {
        // given
        CreateIngredientDto dto = DataFactoryForMapper.createIngredientDto();

        // when
        Ingredient actual = mapper.toModel(dto);

        // then
        Ingredient expected = new Ingredient();
        expected.setName("Sugar");
        expected.setUnit("kg");
        expected.setPricePerUnit(25.99);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "dishes")
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Checks the mapping from model to DTO")
    void toDto_WithValidIngredientEntity_ShouldMapCorrectly() {
        // given
        Ingredient ingredient = DataFactoryForMapper.ingredientEntity();
        IngredientDto expected = new IngredientDto(1L, "Salt", "kg", 35.5);
        // when
        IngredientDto actual = mapper.toDto(ingredient);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Checks the existing object is updated with fields from Dto")
    void updateFromDto_WithValidUpdateDto_ShouldUpdateFieldsCorrectly() {
        // given
        Ingredient ingredient = DataFactoryForMapper.ingredientEntity();
        UpdateIngredientDto updateDto = DataFactoryForMapper.updateIngredientDto();

        Ingredient expected = new Ingredient();
        expected.setName("New Name");
        expected.setUnit("some unit");
        expected.setPricePerUnit(2.5);

        // when
        Ingredient actual = mapper.updateFromDto(ingredient, updateDto);

        // then
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "dishes")
                .isEqualTo(expected);
    }
}

