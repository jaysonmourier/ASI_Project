package com.miage.repository;

import com.miage.dao.PanierDao;
import com.miage.models.Panier;

public class PanierRepository {

    private PanierDao panierDao;

    public PanierRepository() {
        panierDao = new PanierDao();
    }

    public Panier getPanier() {
        return panierDao.getPanier();
    }

    public void ajouterArticle(int articleId, int quantite) {
        panierDao.ajouterArticle(articleId, quantite);
    }

    public void modifierQuantiteArticle(int articleId, int quantite) {
        panierDao.modifierQuantiteArticle(articleId, quantite);
    }

    public void retirerArticle(int articleId) {
        panierDao.retirerArticle(articleId);
    }
}
