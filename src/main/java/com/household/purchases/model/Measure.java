package com.household.purchases.model;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public enum Measure {
    KILOGRAM("kg"),
    GRAM("gm"),
    LITER("l"),
    MILLILITER("ml"),
    PIECES("pcs"),
    TABLESPOON("tbsp"),
    TEASPOON("tsp");

    private final String measureName;

    public String getMeasureName() {
        return measureName;
    }

    public static Measure getByType(String type) {
        for (Measure item : Measure.values()) {
            if (Objects.equals(item.getMeasureName(), type)) {
                return item;
            }
        }
        return null;
    }
}
