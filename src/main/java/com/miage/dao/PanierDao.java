package com.miage.dao;

import com.miage.models.Article;
import com.miage.models.Panier;
import com.miage.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PanierDao {

    private DatabaseService databaseService;
    private ArticleDao articleDao;

    public PanierDao() {
        databaseService = DatabaseService.getInstance();
        articleDao = new ArticleDao();
    }

    public Panier getPanier() {
        Panier panier = new Panier(1);

        String query = "SELECT * FROM panier";

        try {
            ResultSet resultSet = databaseService.query(query);
            while (resultSet.next()) {
                int articleId = resultSet.getInt("article_id");
                int quantite = resultSet.getInt("quantite");

                Article article = articleDao.getArticleById(articleId);
                panier.ajouterArticle(article, quantite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return panier;
    }

    public boolean ajouterArticle(int articleId, int quantite) {
        String query = "INSERT INTO panier (article_id, quantite) VALUES (?, ?) ON DUPLICATE KEY UPDATE quantite = quantite + ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, articleId);
            databaseService.setInt(pstmt, 2, quantite);
            databaseService.setInt(pstmt, 3, quantite);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean modifierQuantiteArticle(int articleId, int quantite) {
        String query = "UPDATE panier SET quantite = ? WHERE article_id = ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, quantite);
            databaseService.setInt(pstmt, 2, articleId);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean retirerArticle(int articleId) {
        String query = "DELETE FROM panier WHERE article_id = ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, articleId);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
