package com.example.jorge.ocafe;

/**
 * Created by jorge on 13/01/2018.
 */

public class Product {
    private long id;
    private String category;
    private String name;
    private String description;
    private String image;
    private double price;
    private int stock;

    public Product(String category, String name, String description, String image, double price, int stock) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public double getPrice() {
        return this.price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return this.stock;
    }
}
