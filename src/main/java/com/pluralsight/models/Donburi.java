package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Donburi extends MenuItem {
    private DonburiSize size;
    private List<Topping> toppings = new ArrayList<>();

    public Donburi(DonburiSize size) {
        super("Donburi", size.getBasePrice());
        this.size = size;
    }



    public List<Topping> getListOfPremium() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.PREMIUM)
                .toList();
    }

    public List<Topping> getListOfAromaOil() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.AROMA_OIL)
                .toList();
    }

    public List<Topping> getListOfRegular() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.REGULAR)
                .toList();
    }

    public List<Topping> getListOfSauces() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.SAUCE)
                .toList();
    }

    public List<Topping> getListOfSoup() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.SOUP)
                .toList();
    }
}
