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

    public boolean insertCategory(Category category, Integer parentId) {
        String query = "INSERT INTO categories (name, parent_id) VALUES (?, ?)";
        boolean success = false;

        try {
            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, category.getName());

            if(parentId != null) {
                System.out.println(parentId + " " + category.getId());
                if(parentId == category.getId()) return false;

                if(parentId <= 0) {
                    databaseService.setNull(pstmt, 2);
                } else {
                    databaseService.setInt(pstmt, 2, parentId);
                }
            } else {
                databaseService.setNull(pstmt, 2);
            }

            success = databaseService.executeUpdate(pstmt) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateCategory(Category newCategory, Category toUpdate) {
        String query = "UPDATE categories SET name = ?, parent_id = ? WHERE name = ?";
        boolean success = false;

        try {

            System.out.println(newCategory);
            System.out.println(toUpdate);

            if(newCategory.getParentId() == toUpdate.getId()) return false;

            PreparedStatement pstmt = databaseService.prepareStatement(query);
            databaseService.setString(pstmt, 1, newCategory.getName());
            databaseService.setInt(pstmt, 2, newCategory.getParentId());
            databaseService.setString(pstmt, 3, toUpdate.getName());

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

    public boolean deleteCategoryByName(String name) {
        boolean success = false;

        try {
            // Récupérer l'ID de la catégorie en utilisant son nom
            Category category = getCategoryByName(name);
            if (category == null) {
                return false;
            }
            int categoryId = category.getId();

            // Supprimer les enregistrements dont le parent_id correspond à categoryId
            String deleteSubCategoriesQuery = "DELETE FROM categories WHERE parent_id = ?";
            PreparedStatement pstmtSubCategories = databaseService.prepareStatement(deleteSubCategoriesQuery);
            databaseService.setInt(pstmtSubCategories, 1, categoryId);
            databaseService.executeUpdate(pstmtSubCategories);

            // Supprimer la catégorie elle-même
            String deleteCategoryQuery = "DELETE FROM categories WHERE name = ?";
            PreparedStatement pstmtCategory = databaseService.prepareStatement(deleteCategoryQuery);
            databaseService.setString(pstmtCategory, 1, name);
            success = databaseService.executeUpdate(pstmtCategory) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
