package com.example.jorge.ocafe;

public class Product {
    private long id;
    private String category;
    private String name;
    private String description;
    private String image;
    private double price;
    private int stock;
    private int stockOnList;

    public Product(String category, String name, String description, String image, double price, int stock) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.stockOnList = 0;
    }

    public Product(long id, String category, String name, String description, String image, double price, int stock) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.stock = stock;
        this.stockOnList = 0;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
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

    public int getStock() {
        return this.stock;
    }

    public int getStockOnList() {return stockOnList;}

    public void editOrderAdd() {
            this.stock--;
            this.stockOnList++;
    }

    public void editOrderRemove() {
        this.stockOnList--;
        this.stock++;
    }

    public void resetStockOnList() {
        if (this.stockOnList > 0){
        this.stock += this.stockOnList;
        this.stockOnList = 0;
        }
    }

    public void confirmStockOnListForProduct() {
        this.stockOnList = 0;
    }

    public void addStock() {
        this.stock += 10;
    }
}