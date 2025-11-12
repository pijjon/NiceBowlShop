package com.pluralsight.models;

public abstract class MenuItem {
    protected String name;
    protected double basePrice;

    public MenuItem(String name, double basePrice) {
        this.basePrice = basePrice;
        this.name = name;
    }

    public abstract double getPrice();

}
