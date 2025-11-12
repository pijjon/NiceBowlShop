package com.pluralsight.models;

public class Karaage extends MenuItem {
    public Karaage(String name, double price) {
        super("Karaage Order", 6.00);

    }

    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
