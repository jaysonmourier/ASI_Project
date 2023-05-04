package com.miage.dao;

import com.miage.models.Article;
import com.miage.models.Category;
import com.miage.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {

    private DatabaseService databaseService;
    private CategoryDao categoryDao;

    public ArticleDao() {
        databaseService = DatabaseService.getInstance();
        categoryDao = new CategoryDao();
    }

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles";

        try {
            ResultSet resultSet = databaseService.query(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String label = resultSet.getString("label");
                String brand = resultSet.getString("brand");
                double price = resultSet.getDouble("price");
                int categoryId = resultSet.getInt("category_id");
                String url = resultSet.getString("url");

                Article article = new Article(id, label, brand, price, categoryId, url);
                articles.add(article);
            }
        } catch (SQLException e) {
                e.printStackTrace();
    }
        return articles;
    }

    public Article getArticleById(int articleId) {
        Article article = null;
        String query = "SELECT * FROM articles WHERE id = ?";

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, articleId);
            ResultSet resultSet = databaseService.execute(pstmt);

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String label = resultSet.getString("label");
                String brand = resultSet.getString("brand");
                double price = resultSet.getDouble("price");
                int categoryId = resultSet.getInt("category_id");
                String url = resultSet.getString("url");

                article = new Article(label, brand, price, categoryId, url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return article;
    }

    public boolean insertArticle(Article article) {
        String query = "INSERT INTO articles (label, brand, price, category_id, url) VALUES (?, ?, ?, ?, ?)";
        boolean success = false;

        try {

            if(categoryDao.getCategoryById(article.getCategory()) == null)
                return false;

            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, article.getLabel());
            databaseService.setString(pstmt, 2, article.getBrand());
            databaseService.setDouble(pstmt, 3, article.getPrice());
            databaseService.setInt(pstmt, 4, article.getCategory());
            databaseService.setString(pstmt, 5, article.getUrl());

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    public boolean updateArticle(Article article, int articleId) {
        String query = "UPDATE articles SET label = ?, brand = ?, price = ?, category_id = ?, url = ? WHERE id = ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, article.getLabel());
            databaseService.setString(pstmt, 2, article.getBrand());
            databaseService.setDouble(pstmt, 3, article.getPrice());
            databaseService.setInt(pstmt, 4, article.getCategory());
            databaseService.setString(pstmt, 5, article.getUrl());
            databaseService.setInt(pstmt, 6, articleId);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean deleteArticle(int articleId) {
        String query = "DELETE FROM articles WHERE id = ?";
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


