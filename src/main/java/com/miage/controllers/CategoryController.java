package com.miage.controllers;

import com.miage.dao.CategoryDao;
import com.miage.models.Category;
import com.miage.models.CategoryResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/category")
public class CategoryController {

    private CategoryDao categoryDao;

    public CategoryController() {
        categoryDao = new CategoryDao();
    }

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

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoryByName(@PathParam("name") String name) {
        Category category = categoryDao.getCategoryByName(name);
        if (category == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found for name: " + name).build();
        }
        return Response.ok(category).entity(new CategoryResponse(category.getName(), category.getParentId())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(Category category) {
        Category existingCategory = categoryDao.getCategoryByName(category.getName());
        if (existingCategory != null) {
            return Response.status(Response.Status.CONFLICT).entity("Category already exists.").build();
        }

        if (categoryDao.insertCategory(category, category.getParentId())) {
            CategoryResponse categoryResponse = new CategoryResponse(category.getName(), category.getParentId());
            return Response.status(Response.Status.CREATED).entity(categoryResponse).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating category.").build();
        }
    }

    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("name") String name, Category newCategory) {
        Category toUpdate = categoryDao.getCategoryByName(name);

        if(toUpdate == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Category not found for name: " + name)
                    .build();

        if (categoryDao.updateCategory(newCategory, toUpdate)) {
            return Response.ok(newCategory).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Internal error.").build();
        }
    }

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("name") String name) {
        if (categoryDao.deleteCategoryByName(name)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found for name: " + name).build();
        }
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
