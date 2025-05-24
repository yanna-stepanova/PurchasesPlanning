package com.household.purchases.util;

import com.household.purchases.dto.ingredient.CreateIngredientDto;
import com.household.purchases.dto.ingredient.UpdateIngredientDto;
import com.household.purchases.model.Ingredient;

public class DataFactoryForMapper {
    public static CreateIngredientDto createIngredientDto() {
        return new CreateIngredientDto("Sugar", "kg", 25.99);
    }

    public static Ingredient ingredientEntity() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("Salt");
        ingredient.setUnit("kg");
        ingredient.setPricePerUnit(35.5);
        return ingredient;
    }

    public static UpdateIngredientDto updateIngredientDto() {
        return new UpdateIngredientDto("New Name", "some unit", 2.5);
    }
}
