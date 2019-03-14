package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface IPartyRestService {

    @GET
    @Path("/Parties")
    @Produces(MediaType.TEXT_XML)
    List<Party> getAllParties();

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_XML)
    Party getParty(@PathParam("name") String name);


    @POST
    @Consumes(MediaType.TEXT_XML)
    void addParty(Party party);

    @DELETE
    @Path("/{name}")
    Response deleteParty(@PathParam("name") String name);

    @PUT
    @Consumes(MediaType.TEXT_XML)
    void changeParty(Party party);

    @GET
    @Path("/{partyname}/guests")
    @Produces(MediaType.TEXT_XML)
    List<Guest> getPartyGuests(@PathParam("partyname") String name);

    @GET
    @Path("/{name}/items")
    @Produces(MediaType.TEXT_XML)
    List<Item> getPartyItems(@PathParam("name") String name);
    @GET
    @Path("/{name}/ingredients")
    @Produces(MediaType.TEXT_XML)
    List<Item> getPartyIngredients(@PathParam("name") String name);

}
