package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    protected String customer;
    protected String orderName;
    protected List<MenuItem> items = new ArrayList<>();

    public Order(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<MenuItem> getAllItems() {
        return items;
    }

    public List<Donburi> getDonburiItems() {
        return this.items.stream()
                .filter(item -> item instanceof Donburi)
                .map(item -> (Donburi) item)
                .toList();
    }

    public List<Drink> getDrinkItems() {
        return this.items.stream()
                .filter(item -> item instanceof Drink)
                .map(item -> (Drink) item)
                .toList();
    }

    public List<Soup> getSoupItems() {
        return this.items.stream()
                .filter(item -> item instanceof Soup)
                .map(item -> (Soup) item)
                .toList();
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public double getOrderTotal() {
        double total = 0;
        for (MenuItem item : this.items) {
            total += item.getPrice();
        }
        return total;
    }

}
