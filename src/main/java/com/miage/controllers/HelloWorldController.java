package com.miage.controllers;

import com.miage.dao.CategoryDao;
import com.miage.models.Category;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorldController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {

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

        categoryDao.insertCategory(subcategory, category.getId());
        category = categoryDao.getCategoryByName("Ordinateurs portables");
        int id1 = category.getId();
        categoryDao.insertCategory(subcategory2, id);
        category = categoryDao.getCategoryByName("Ordinateurs fixes");
        int id2 = category.getId();


        categoryDao.insertCategory(subcategory3, id2);

        categoryDao.insertCategory(subcategory4, id1);
        categoryDao.insertCategory(subcategory5, id1);


        return "success";
    }
}
