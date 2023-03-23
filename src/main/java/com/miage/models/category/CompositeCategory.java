package com.miage.models.category;

import java.util.ArrayList;
import java.util.List;

public class CompositeCategory extends Category {
    private List<Category> categories = new ArrayList<Category>();

    public CompositeCategory(String name) {
        super(name);
    }

    public void print() {
        System.out.println("Category: " + getName());
        for (Category category : categories) {
            category.print();
        }
    }

    public void add(Category category) {
        categories.add(category);
    }

    public void remove(Category category) {
        categories.remove(category);
    }
}
