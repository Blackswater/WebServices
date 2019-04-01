package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * @author Pascal Röcker
 * Non-implemented PARTY-Rest service
 */
public interface IPartyRestService {

    @GET
    @Path("/Parties")
    @Produces(MediaType.APPLICATION_JSON)
    List<Party> getAllParties();

    @POST

    @Consumes(MediaType.APPLICATION_JSON)
    void addParty(Party party);

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Party getParty(@PathParam("name") String name);


    @POST
    @Consumes
    @Path("/newParty")
    @Produces(MediaType.APPLICATION_JSON)
    Party newParty();

    @DELETE
    @Path("/{name}")
    Response deleteParty(@PathParam("name") String name);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void changeParty(Party party);

    @GET
    @Path("/{partyname}/guests")
    @Produces(MediaType.APPLICATION_JSON)
    List<Guest> getPartyGuests(@PathParam("partyname") String name);

    @GET
    @Path("/{name}/items")
    @Produces(MediaType.APPLICATION_JSON)
    List<Item> getPartyItems(@PathParam("name") String name);
    @GET
    @Path("/{name}/ingredients")
    @Produces(MediaType.APPLICATION_JSON)
    List<Ingredient> getPartyIngredients(@PathParam("name") String name);

}
