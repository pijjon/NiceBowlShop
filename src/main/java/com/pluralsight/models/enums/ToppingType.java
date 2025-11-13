package com.pluralsight.models.enums;

public enum ToppingType {
    PREMIUM,
    AROMA_OIL,
    REGULAR,
    SAUCE,
    SIDE;

    // will check if "this"/current instance of the ToppingType enum is paid or free
    public boolean isBillable() {
        return this == PREMIUM || this == AROMA_OIL;
    }
}
