package com.miage.controllers;

import com.google.gson.Gson;
import com.miage.dao.ArticleDao;
import com.miage.models.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articles")
public class ArticleController {

    private ArticleDao articleDao;

    public ArticleController() {
        articleDao = new ArticleDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllArticles() {
        List<Article> articles = articleDao.getAllArticles();
        Gson gson = new Gson();
        return gson.toJson(articles);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{id}")
    public String getAllArticlesInCategory(@PathParam("id") int id) {
        List<Article> articles = articleDao.getAllArticlesByCategory(id);
        Gson gson = new Gson();
        return gson.toJson(articles);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getArticleById(@PathParam("id") int id) {
        return articleDao.getArticleById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article createArticle(Article article) {
        if (articleDao.insertArticle(article)) {
            return article;
        } else {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Article updateArticle(@PathParam("id") int id, Article article) {
        if (articleDao.updateArticle(article, id)) {
            return article;
        } else {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArticle(@PathParam("id") int id) {
        if (articleDao.deleteArticle(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}