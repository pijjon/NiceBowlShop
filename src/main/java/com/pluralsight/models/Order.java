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

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public double getOrderTotal() {
        double total = 0;
        // iterate through items and sum up each MenuItem price
        // return sum
        return total;
    }

}
