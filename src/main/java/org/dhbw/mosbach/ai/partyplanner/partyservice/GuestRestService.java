package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.core.Response;

public class GuestRestService implements IGuestRestService
{
    @Override
    public Guest getGuest(final String code)
    {
        return null;
    }

    @Override
    public Response deleteGuest(final String name)
    {
        return null;
    }

    @Override
    public void changeGuest(final Guest guest)
    {

    }

    @Override
    public void modifyGuestforParty(final String name,
        final Guest guest)
    {

    }
}
