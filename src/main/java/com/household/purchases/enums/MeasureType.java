package com.household.purchases.enums;

import java.util.Objects;

public enum MeasureType {
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

    MeasureType(String typeName) {this.measureName = typeName;}

    public static MeasureType getByType(String type) {
        for (MeasureType item : MeasureType.values()) {
            if (Objects.equals(item.getMeasureName(), type)) {
                return item;
            }
        }
        return null;
    }
}
