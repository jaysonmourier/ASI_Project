package com.miage.models;

import com.miage.models.category.Category;

public class ArticleModel {

    private String libelle;
    private String marque;
    private Float prix;
    private Category c;

    private String photo;


    public ArticleModel(String libelle, String marque, Float prix, Category c, String photo){
        this.libelle= libelle;
        this.marque = marque;
        this.prix = prix;
        this.c=c;
        this.photo=photo;
    };

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Category getC() {
        return c;
    }

    public void setC(Category c) {
        this.c = c;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
