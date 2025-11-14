package com.pluralsight.models;

import com.pluralsight.models.enums.ToppingItem;
import com.pluralsight.models.enums.ToppingType;

public class Topping {
    private final String name;
    protected final ToppingType type;

    // constructor
    public Topping(ToppingItem toppingItem, ToppingType type) {
        this.name = toppingItem.getDisplayName();
        this.type = type;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }
}
