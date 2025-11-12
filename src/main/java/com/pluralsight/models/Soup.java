package com.pluralsight.models;

public class Soup extends MenuItem {
    public Soup(String name, double price) {
        super("Soup Order", 6.00);

    }

    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
