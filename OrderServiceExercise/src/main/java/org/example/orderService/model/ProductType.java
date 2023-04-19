package org.example.orderService.model;

public enum ProductType {
    APPLE("apple", 0.6),
    ORANGE("orange", 0.25);

    private final String value;
    private final double price;

    ProductType(String value, double price) {
        this.value = value;
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public double getPrice() {
        return price;
    }
}
