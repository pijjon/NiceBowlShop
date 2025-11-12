package com.pluralsight.models.enums;

public enum DrinkSize {
    // these statements implicitly call the constructor defined below to instantiate an enum with price property assigned
    REGULAR(5.00),
    LARGE(7.00);

    // stores price for each enum instantiation
    private final double price;

    // implicitly private
    DrinkSize(double price) {
        this.price = price;
    }

    // method for retrieving the price based on size
    public double getPrice() {
        return price;
    }
}
