package com.miage.models;

import java.util.ArrayList;
import java.util.List;

public class Category {
        private int id;
        private String name;
        private Integer categorieParentId;
        private List<Category> sousCategories;

        public Category() { this.sousCategories = new ArrayList<>(); this.categorieParentId = 0; }
        public Category(String name)
        {
            this.name = name;
            this.categorieParentId = 0;
            this.sousCategories = new ArrayList<>();
        }

    public Category(String name, Integer categorieParentId) {
        this.name = name;
        this.categorieParentId = categorieParentId;
        this.sousCategories = new ArrayList<>();
    }

        public Category(int id, String name, Integer categorieParentId) {
        this.id = id;
        this.name = name;
        this.categorieParentId = categorieParentId;
        this.sousCategories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNom(String nom) {
        this.name = nom;
    }

    public Integer getParentId() {
        return categorieParentId;
    }

    public void setCategorieParentId(Integer categorieParentId) {
        this.categorieParentId = categorieParentId;
    }

    public List<Category> getSubCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<Category> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public String toString()
    {
        return "Catégorie : " + this.name + " " + String.valueOf(this.categorieParentId);
    }
}
