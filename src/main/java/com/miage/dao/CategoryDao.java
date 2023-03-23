package com.miage.dao;

import com.miage.models.Category;
import com.miage.services.DatabaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDao {

    private DatabaseService databaseService;

    public CategoryDao() {
        databaseService = DatabaseService.getInstance();
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try {
            ResultSet resultSet = databaseService.query(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int parentId = resultSet.getInt("parent_id");

                Category category = new Category(id, name, parentId);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Map<Integer, Category> categorieMap = new HashMap<>();
        DatabaseService databaseService = DatabaseService.getInstance();

        try {
            ResultSet resultSet = databaseService.query("SELECT * FROM categories");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("name");
                Integer categorieParentId = resultSet.getObject("parent_id", Integer.class);

                Category categorie = new Category(id, nom, categorieParentId);
                categorieMap.put(id, categorie);
            }

            for (Category categorie : categorieMap.values()) {
                Integer parentId = categorie.getParentId();
                if (parentId != null) {
                    Category parent = categorieMap.get(parentId);
                    if (parent != null) {
                        parent.getSubCategories().add(categorie);
                    }
                } else {
                    categories.add(categorie);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }


    public Category getCategoryById(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM categories WHERE id = ?";

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, categoryId);
            ResultSet resultSet = databaseService.execute(pstmt);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int parentId = resultSet.getInt("parent_id");
                category = new Category(id, name, parentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public Category getCategoryByName(String name) {
        Category category = null;
        String query = "SELECT * FROM categories WHERE name = ?";
        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, name);
            ResultSet resultSet = databaseService.execute(pstmt);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");
                category = new Category(id, name, parentId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public boolean insertCategory(Category category, int parentId) {
        String query = "INSERT INTO categories (name, parent_id) VALUES (?, ?)";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, category.getName());

            if(parentId <= 0) {
                databaseService.setNull(pstmt, 2);
            } else {
                databaseService.setInt(pstmt, 2, parentId);
            }

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateCategory(Category category, int categoryId) {
        String query = "UPDATE categories SET name = ? WHERE id = ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, category.getName());
            databaseService.setInt(pstmt, 2, categoryId);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean deleteCategory(int categoryId) {
        String query = "DELETE FROM categories WHERE id = ?";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setInt(pstmt, 1, categoryId);

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
