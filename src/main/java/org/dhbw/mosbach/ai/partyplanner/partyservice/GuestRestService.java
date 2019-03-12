package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface GuestRestService {

    @GET
    @Path("/party/{partyname}/guests")
    @Produces(MediaType.TEXT_XML)
    List<Guest> getGuestsForParty(@PathParam("partyname") String name);

    @GET
    @Path("/guests/{name}")
    @Produces(MediaType.TEXT_XML)
    Party getParty(@PathParam("name") String name);

    @POST
    @Consumes(MediaType.TEXT_XML)
    void addGuest(Guest guest);

    @DELETE
    @Path("/{name}")
    Response deleteGuest(@PathParam("name") String name);

    @PUT
    @Consumes(MediaType.TEXT_XML)
    void changeGuest(Guest guest);
}
