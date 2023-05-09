package com.miage.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miage.dao.CategoryDao;
import com.miage.models.Category;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/name")
    public String name(String json)
    {
        System.out.println("JSON OBJ : ");
        Gson gson = new Gson();
        System.out.println("1");
        String name = gson.fromJson(json, String.class);
        System.out.println("2");
        return "name = " + name;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hydrate")
    public String hydrate() {
        CategoryDao categoryDao = new CategoryDao();

        Category category = new Category("Ordinateurs");
        Category subcategory = new Category("Ordinateurs portables");
        Category subcategory2 = new Category("Ordinateurs fixes");
        Category subcategory3 = new Category("Dell");
        Category subcategory4 = new Category("Alienware");
        Category subcategory5 = new Category("Apple");


        categoryDao.insertCategory(category,  null);
        category = categoryDao.getCategoryByName("Ordinateurs");

        int id = category.getId();

        categoryDao.insertCategory(subcategory, id);

        category = categoryDao.getCategoryByName("Ordinateurs portables");
        int id1 = category.getId();

        System.out.println("id1 = " + id1);

        categoryDao.insertCategory(subcategory2, id);
        category = categoryDao.getCategoryByName("Ordinateurs fixes");
        int id2 = category.getId();

        System.out.println("id2 = " + id2);

        categoryDao.insertCategory(subcategory3, id2);

        categoryDao.insertCategory(subcategory4, id1);
        categoryDao.insertCategory(subcategory5, id1);
        return "Hydrated";
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
        return "Hello, world!";
    }
}
