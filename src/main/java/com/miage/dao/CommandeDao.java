package com.miage.dao;

import com.miage.models.Article;
import com.miage.models.Commande;
import com.miage.models.Panier;
import com.miage.services.DatabaseService;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandeDao {
    private DatabaseService databaseService;

    public CommandeDao() {
        databaseService = DatabaseService.getInstance();
    }

    public Commande validerCommande(Panier panier) {
        Commande commande = null;
        String insertCommandeQuery = "INSERT INTO commandes (date) VALUES (?)";
        String insertLigneCommandeQuery = "INSERT INTO lignes_commande (commande_id, article_id, quantite) VALUES (?, ?, ?)";

        try {
            // Insertion de la commande
            PreparedStatement pstmt = databaseService.prepareStatement(insertCommandeQuery, Statement.RETURN_GENERATED_KEYS);
            databaseService.setString(pstmt, 1, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            int affectedRows = databaseService.executeUpdate(pstmt);

            if (affectedRows == 0) {
                throw new SQLException("La création de la commande a échoué, aucune ligne affectée.");
            }

            // Récupération de l'ID de la commande
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int commandeId = -1;
            if (generatedKeys.next()) {
                commandeId = generatedKeys.getInt(1);
            }

            if (commandeId == -1) {
                throw new SQLException("La création de la commande a échoué, aucun ID obtenu.");
            }

            // Insertion des lignes de commande
            pstmt = databaseService.prepareStatement(insertLigneCommandeQuery, Statement.RETURN_GENERATED_KEYS);

            for (Map.Entry<Article, Integer> entry : panier.getArticles().entrySet()) {
                databaseService.setInt(pstmt, 1, commandeId);
                databaseService.setInt(pstmt, 2, entry.getKey().getId());
                databaseService.setInt(pstmt, 3, entry.getValue());
                pstmt.addBatch();
            }

            pstmt.executeBatch();

            // Création de l'objet Commande
            commande = new Commande(commandeId, LocalDateTime.now());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commande;
    }

    public List<Commande> getAllCommandes() {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM commandes";

        try {
            ResultSet resultSet = databaseService.query(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
                Commande commande = new Commande(id, date);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return commandes;
    }

}
