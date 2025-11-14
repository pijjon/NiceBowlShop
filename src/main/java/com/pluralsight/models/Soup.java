package com.pluralsight.models;

import com.pluralsight.models.enums.SoupName;

public class Soup extends MenuItem {
    // constructor
    public Soup(SoupName soupName) {
        super(soupName.name(), 6.00);
    }

    // returns basePrice
    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
