package com.miage.repository;

import com.miage.dao.CommandeDao;
import com.miage.models.Commande;
import com.miage.models.Panier;

import java.util.List;

public class CommandeRepository {
    private CommandeDao commandeDao;

    public CommandeRepository() {
        commandeDao = new CommandeDao();
    }

    public List<Commande> getAllCommandes() {
        return commandeDao.getAllCommandes();
    }

    public Commande validerCommande(Panier panier) {
        return commandeDao.validerCommande(panier);
    }
}
