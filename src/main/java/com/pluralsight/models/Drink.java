package com.pluralsight.models;

import com.pluralsight.models.enums.DrinkName;
import com.pluralsight.models.enums.DrinkSize;

public class Drink extends MenuItem {
    private DrinkSize size;

    // constructor
    public Drink(DrinkName drinkName, DrinkSize size) {
        super(drinkName.name(), size.getPrice()); // just beer for now until other drinks get added as an option
        this.size = size;
    }

    // return size
    public DrinkSize getSize() {
        return size;
    }

    //every time size is changed, price will change update
    public void setSize(DrinkSize size) {
        this.size = size;
        this.basePrice = this.getSize().getPrice();
    }

    // return price
    @Override
    public double getPrice() {
        return this.basePrice;
    }
}
