package tn.esprit.rest.ressource;

import tn.esprit.rest.entities.Rendezvous;
import tn.esprit.rest.metiers.RendezvousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("Rendezvous")
public class RendezvousRessources {

    public static RendezvousBusiness rendezVousMetier = new RendezvousBusiness();

    // Add a new rendezvous
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON) // Specify that this method consumes JSON
    @Produces(MediaType.APPLICATION_JSON) // Specify that this method returns a response in JSON
    public Response addrendezVous(Rendezvous r) {
        if (rendezVousMetier.addRendezVous(r)) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    // Get list of rendezvous, or filter by logement reference
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVous(@QueryParam("refLogement") String refLogement) {
        List<Rendezvous> liste = new ArrayList<>();
        if (refLogement != null) {
            liste = rendezVousMetier.getListeRendezVousByLogementReference(Integer.parseInt(refLogement));
        } else {
            liste = rendezVousMetier.getListeRendezVous();
        }

        if (liste.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(liste).build();
    }

    // Update an existing rendezvous by its ID
    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRdv(Rendezvous updatedRendezVous, @PathParam("id") int id) {
        if (rendezVousMetier.updateRendezVous(id, updatedRendezVous)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // Delete a rendezvous by its ID
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRendezVous(@PathParam("id") int id) {
        if (rendezVousMetier.deleteRendezVous(id)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // Get a single rendezvous by its ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousbyId(@PathParam("id") int id) {
        Rendezvous rendezvous = rendezVousMetier.getRendezVousById(id);
        if (rendezvous != null) {
            return Response.status(Response.Status.OK).entity(rendezvous).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
