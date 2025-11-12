package com.pluralsight.models;

public enum ToppingType {
    PREMIUM,
    AROMA_OIL,
    REGULAR,
    SAUCE,
    SOUP;

    public boolean isPaid() {
        return this == PREMIUM || this == AROMA_OIL;
    }
}
