package com.household.purchases.enums;

import com.household.purchases.exception.InvalidEnumCodeException;

import java.util.Arrays;

public enum MeasurementType {
    G("г"),
    KG("кг"),
    ML("мл"),
    L("л"),
    PCS("шт"),
    TSP("ч.л"),
    TBSP("ст.л");

    private final String codeName;

    MeasurementType(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public static MeasurementType getCode(String unitName) {
        return Arrays.stream(MeasurementType.values())
                .filter(unit -> unit.getCodeName().equalsIgnoreCase(unitName))
                .findFirst()
                .orElseThrow(() -> new InvalidEnumCodeException(MeasurementType.class, unitName));
    }
}
