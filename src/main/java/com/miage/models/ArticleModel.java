package com.miage.models;

import com.miage.models.category.Category;

public class ArticleModel {
    private String label;
    private String brand;
    private double price;
    private Category category;
    private String url;

    public ArticleModel(String label, String brand, double price, Category category, String url) {
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
>>>>>>> 462a02ebf1c3d3e62cfab81a2e7d1ca661757a2c
    }
}
