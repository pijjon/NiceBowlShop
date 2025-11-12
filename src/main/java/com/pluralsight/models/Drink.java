package com.pluralsight.models;

public class Drink extends MenuItem {
    private DrinkSize size;

    public Drink(String name, DrinkSize size) {
        super("Beer", size.getPrice()); // just beer for now until other drinks get added as an option
        this.size = size;
    }

    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
