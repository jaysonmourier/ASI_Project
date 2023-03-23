package com.miage.models.category;

import java.util.ArrayList;
import java.util.List;

public class CompositeCategory extends Category {
    private List<Category> categories = new ArrayList<Category>();

    public CompositeCategory(String name) {
        super(name);
    }

    public CompositeCategory(int id, String name) {
        super(id, name);
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

    public String toString()
    {
        String str = new String();

        str = "Téléphones :\n";

        for (Category category : categories) {
            str += "\t" + category + "\n";
        }

        return str;
    }
}
