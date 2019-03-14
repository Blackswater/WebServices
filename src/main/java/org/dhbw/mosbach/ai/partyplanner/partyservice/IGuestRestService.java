package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface IGuestRestService
{

    @GET
    @Path("/guests/{code}")
    @Produces(MediaType.TEXT_XML)
    Guest getGuest(@PathParam("code") String code);

    @DELETE
    @Path("/guest/{name}")
    Response deleteGuest(@PathParam("name") String name);

    @PUT
    @Consumes(MediaType.TEXT_XML)
    void changeGuest(Guest guest);

    @PUT
    @Consumes(MediaType.TEXT_XML)
    @Path("/party/{name}/guest")
    void modifyGuestforParty(@PathParam("name") String name,Guest guest);



}
