package com.pluralsight.models;

public abstract class MenuItem {
    protected String name;
    protected double basePrice;

    // constructor
    public MenuItem(String name, double basePrice) {
        this.basePrice = basePrice;
        this.name = name;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract double getPrice();

}
