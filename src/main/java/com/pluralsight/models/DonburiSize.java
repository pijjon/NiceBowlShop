package com.pluralsight.models;

public enum DonburiSize {
    // these statements implicitly call the constructor defined below to instantiate an enum with basePrice property assigned
    SMALL(10.00, 2.00, 1.00, .50, .20),
    MEDIUM(11.00, 2.50, 1.25, .75, .40),
    LARGE(12.00, 3.00, 1.50, 1.00, .60);

    // stores basePrice as property in enum
    private final double basePrice;
    private final double firstPrem;
    private final double extraPrem;
    private final double firstOil;
    private final double extraOil;

    // implicit private constructor
    DonburiSize(double basePrice, double firstPrem, double extraPrem, double firstOil, double extraOil) {
        this.basePrice = basePrice;
        this.firstPrem = firstPrem;
        this.extraPrem = extraPrem;
        this.firstOil = firstOil;
        this.extraOil = extraOil;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public double getFirstPrem() {
        return firstPrem;
    }

    public double getExtraPrem() {
        return extraPrem;
    }

    public double getFirstOil() {
        return firstOil;
    }

    public double getExtraOil() {
        return extraOil;
    }
}
