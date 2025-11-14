package com.pluralsight.models;

import com.pluralsight.models.enums.ToppingItem;
import com.pluralsight.models.enums.ToppingType;

public class Topping {
    private final String name;
    protected final ToppingType type;

    public Topping(ToppingItem toppingItem, ToppingType type) {
        this.name = toppingItem.getDisplayName();
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ToppingType getType() {
        return type;
    }
}
