package com.pluta.gocart.module.Product.payload.request;

public class UpdateProductRequest {
    private String name;
    private String newName;
    private double newPrice;
    private String unit;

    public UpdateProductRequest() {
    }

    public UpdateProductRequest(String name, String newName, double newPrice, String unit) {
        this.name = name;
        this.newName = newName;
        this.newPrice = newPrice;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
