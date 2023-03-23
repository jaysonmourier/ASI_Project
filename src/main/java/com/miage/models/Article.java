package com.miage.models;

public class Article {
    private int id;
    private String label;
    private String brand;
    private double price;
    private Category category;
    private String url;

    public Article(String label, String brand, double price, Category category, String url) {
        this.label = label;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.url = url;
    }

    public Article(int id, String label, String brand, double price, Category category, String url) {
        this.id = id;
        this.label = label;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
