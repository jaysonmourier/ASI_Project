package com.miage.controllers;

import com.miage.repository.PanierRepository;
import com.miage.models.Panier;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/panier")
public class PanierController {

    private PanierRepository panierRepository = new PanierRepository(); // Remplacez ceci par votre propre implémentation de répertoire/DAO

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Panier getPanier() {
        return panierRepository.getPanier();
    }

    @POST
    @Path("/ajouter/{articleId}/{quantite}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Panier ajouterArticle(@PathParam("articleId") int articleId, @PathParam("quantite") int quantite) {
        panierRepository.ajouterArticle(articleId, quantite);
        return panierRepository.getPanier();
    }

    @PUT
    @Path("/modifier/{articleId}/{quantite}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Panier modifierQuantiteArticle(@PathParam("articleId") int articleId, @PathParam("quantite") int quantite) {
        panierRepository.modifierQuantiteArticle(articleId, quantite);
        return panierRepository.getPanier();
    }

    @DELETE
    @Path("/retirer/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Panier retirerArticle(@PathParam("articleId") int articleId) {
        panierRepository.retirerArticle(articleId);
        return panierRepository.getPanier();
    }
}
