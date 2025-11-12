package com.pluralsight.models;

import java.util.List;

public class Donburi extends MenuItem {
    private DonburiSize size;
    private List<Topping> toppings = new ArrayList<>();

    public Donburi(DonburiSize size) {
        super("Donburi", size.getBasePrice());
        this.size = size;
    }

    

}
