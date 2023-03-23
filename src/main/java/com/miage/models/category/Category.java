package com.miage.models.category;

public abstract class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void print();

    public abstract void add(Category category);

    public abstract void remove(Category category);
}