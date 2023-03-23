package com.miage.models.category;

public class SubCategory extends Category {
    public SubCategory(String name) {
        super(name);
    }

    public void print() {
        System.out.println("Subcategory: " + getName());
    }

    public void add(Category category) {
        // Ne peut pas ajouter de sous-catégorie à une sous-catégorie
        throw new UnsupportedOperationException();
    }

    public void remove(Category category) {
        // Ne peut pas retirer de sous-catégorie d'une sous-catégorie
        throw new UnsupportedOperationException();
    }
}