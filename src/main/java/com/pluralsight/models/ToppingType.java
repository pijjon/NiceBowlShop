package com.pluralsight.models;

public enum ToppingType {
    PREMIUM,
    AROMA_OIL,
    REGULAR,
    SAUCE,
    SOUP;

    // will check if "this"/current instance of the ToppingType enum is paid or free
    public boolean isBillable() {
        return this == PREMIUM || this == AROMA_OIL;
    }
}
