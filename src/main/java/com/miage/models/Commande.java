package com.miage.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Commande {
    private int id;
    private Panier panier;
    private Date date;

    public Commande(int id, Panier panier, Date date) {
        this.id = id;
        this.panier = panier;
        this.date = date;
    }

    public Commande(int commandeId, LocalDateTime now) {
        this.id = commandeId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
