package com.pluralsight.models;

import com.pluralsight.models.enums.ToppingType;

public class Topping {
    private final String name;
    protected final ToppingType type;

    public Topping(String name, ToppingType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }
}
