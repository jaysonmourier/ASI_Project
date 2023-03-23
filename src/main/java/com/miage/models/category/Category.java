package com.miage.models.category;

public abstract class Category {
    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void print();

    public abstract void add(Category category);

    public abstract void remove(Category category);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}