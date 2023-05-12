package com.miage.controllers;

import com.miage.models.Commande;
import com.miage.models.Panier;
import com.miage.repository.CommandeRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/commandes")
public class CommandeController {
    private CommandeRepository commandeRepository;

    public CommandeController() {
        commandeRepository = new CommandeRepository();
    }

    @POST
    @Path("/valider")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validerCommande(Panier panier) {
        Commande commande = commandeRepository.validerCommande(panier);

        if (commande != null) {
            return Response.status(Response.Status.CREATED).entity(commande).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommandes() {
        List<Commande> commandes = commandeRepository.getAllCommandes();
        return Response.status(Response.Status.OK).entity(commandes).build();
    }
}
