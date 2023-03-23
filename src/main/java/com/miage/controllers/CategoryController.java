package com.miage.controllers;

import com.miage.dao.CategoryDao;
import com.miage.models.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/category")
public class CategoryController {

    public String buildCategoryString(List<Category> categories, String indent) {
        StringBuilder categoryString = new StringBuilder();
        for (Category category : categories) {
            categoryString.append(indent).append(category.getName()).append("\n");
            List<Category> childCategories = getChildCategories(categories, category.getId());
            categoryString.append(buildCategoryString(childCategories, indent + "  "));
        }
        return categoryString.toString();
    }

    public List<Category> getChildCategories(List<Category> allCategories, int parentId) {
        List<Category> childCategories = new ArrayList<>();
        for (Category category : allCategories) {
            if (category.getParentId() == parentId) {
                childCategories.add(category);
            }
        }
        return childCategories;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllCategories() {
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getCategories();
        StringBuilder builder = new StringBuilder();
        afficherCategories(categories, builder, 0);
        return builder.toString();
    }

    public static void afficherCategories(List<Category> categories, StringBuilder builder, int indentation) {
        String indentationSpaces = repeat("\t", indentation); // Utilisez "  " pour chaque niveau d'indentation

        for (Category categorie : categories) {
            builder.append(indentationSpaces)
                    .append(categorie.getName())
                    .append("\n");

            // Récursivement afficher les sous-catégories avec un niveau d'indentation supérieur
            afficherCategories(categorie.getSubCategories(), builder, indentation + 1);
        }
    }

    public static String repeat(String str, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(str);
        }
        return builder.toString();
    }
}
