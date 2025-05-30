package com.household.purchases.mapper;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.IngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.model.Ingredient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("IngredientMapper unit tests")
class IngredientMapperTest {
    private final IngredientMapper mapper = Mappers.getMapper(IngredientMapper.class);

    @Test
    @DisplayName("Convert the CreateIngredientDto to Ingredient entity correctly")
    void toModel_WithValidCreateIngredientDto_Success() {
        // given
        CreateIngredientDto dto = createIngredientDto();

        // when
        Ingredient actual = mapper.toModel(dto);

        // then
        Ingredient expected = new Ingredient();
        expected.setName("Sugar");
        expected.setUnit("kg");
        expected.setPricePerUnit(25.99);

        assertThat(actual).satisfies(
                a -> assertThat(a.getName()).isEqualTo(dto.name()),
                a -> assertThat(a.getUnit()).isEqualTo(dto.unit()),
                a -> assertThat(a.getPricePerUnit()).isCloseTo(dto.pricePerUnit(), within(1e-9))
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "dishes")
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("Checks the mapping from model to DTO")
    void toDto_WithValidIngredientEntity_ShouldMapCorrectly() {
        // given
        Ingredient ingredient = ingredientEntity();
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
        Ingredient ingredient = ingredientEntity();
        UpdateIngredientDto updateDto = updateIngredientDto();

        Ingredient expected = new Ingredient();
        expected.setName("New Name");
        expected.setUnit("some unit");
        expected.setPricePerUnit(2.5);

        // when
        Ingredient actual = mapper.updateFromDto(ingredient, updateDto);

        // then
        assertThat(actual).satisfies(
                a -> assertThat(a.getName()).isEqualTo(updateDto.name()),
                a -> assertThat(a.getUnit()).isEqualTo(updateDto.unit()),
                a -> assertThat(a.getPricePerUnit()).isCloseTo(updateDto.pricePerUnit(), within(1e-9))
        );

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "dishes")
                .isEqualTo(expected);
    }

    private CreateIngredientDto createIngredientDto() {
        return new CreateIngredientDto("Sugar", "kg", 25.99);
    }

    private Ingredient ingredientEntity() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Salt");
        ingredient.setUnit("kg");
        ingredient.setPricePerUnit(35.5);
        return ingredient;
    }

    private UpdateIngredientDto updateIngredientDto() {
        return new UpdateIngredientDto("New Name", "some unit", 2.5);
    }
}

