package com.pluralsight.models;

import com.pluralsight.models.enums.DonburiSize;
import com.pluralsight.models.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;

public class Donburi extends MenuItem {
    private DonburiSize size;
    private List<Topping> toppings = new ArrayList<>();

    public Donburi(DonburiSize size) {
        super("Donburi", size.getBasePrice());
        this.size = size;
    }

    @Override
    public double getPrice() {
        double currentPrice = this.basePrice;
        int premiumCount = this.getListOfPremium().size();
        int aromaCount = this.getListOfAromaOil().size();

        if (premiumCount > 0) {
            currentPrice += size.getFirstPrem() + (premiumCount - 1) * size.getExtraPrem();
        }

        if (aromaCount > 0) {
            currentPrice += size.getFirstOil() + (aromaCount - 1) * size.getExtraOil();
        }

        return currentPrice;

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
