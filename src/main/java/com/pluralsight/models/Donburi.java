package com.pluralsight.models;

import com.pluralsight.models.enums.DonburiSize;
import com.pluralsight.models.enums.DonburiType;
import com.pluralsight.models.enums.SideName;
import com.pluralsight.models.enums.ToppingType;

import java.util.ArrayList;
import java.util.List;

public class Donburi extends MenuItem {
    private DonburiSize size;
    private DonburiType type;
    private final List<Topping> toppings = new ArrayList<>();
    private SideName side;
    private boolean toasted;

    public Donburi(DonburiType donburiType, DonburiSize size) {
        super(donburiType.name(), size.getBasePrice());
        this.size = size;
        this.type = donburiType;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public DonburiSize getSize() {
        return size;
    }

    public void setSize(DonburiSize size) {
        this.size = size;
    }

    public DonburiType getType() {
        return type;
    }

    public void setType(DonburiType type) {
        this.type = type;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    public SideName getSide() {
        return side;
    }

    public void setSide(SideName side) {
        this.side = side;
    }

    @Override
    public double getPrice() {
        double currentPrice = this.basePrice;
        int premiumCount = this.getListOfPremiumToppings().size();
        int aromaCount = this.getListOfAromaOils().size();

        if (premiumCount > 0) {
            currentPrice += size.getFirstPrem() + (premiumCount - 1) * size.getExtraPrem();
        }

        if (aromaCount > 0) {
            currentPrice += size.getFirstOil() + (aromaCount - 1) * size.getExtraOil();
        }

        return currentPrice;
    }

    public List<Topping> getListOfAllToppings() {
        return toppings;
    }

    public List<Topping> getListOfPremiumToppings() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.PREMIUM)
                .toList();
    }

    public List<Topping> getListOfAromaOils() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.AROMA_OIL)
                .toList();
    }

    public List<Topping> getListOfRegularToppings() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.REGULAR)
                .toList();
    }

    public List<Topping> getListOfSauces() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.SAUCE)
                .toList();
    }

    public List<Topping> getListOfSides() {
        return this.toppings.stream()
                .filter(t -> t.type == ToppingType.SIDE)
                .toList();
    }
}
