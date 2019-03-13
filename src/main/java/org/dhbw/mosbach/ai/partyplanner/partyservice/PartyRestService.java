package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.core.Response;
import java.util.List;

public class PartyRestService implements IPartyRestService
{
    @Override
    public List<Party> getAllParties()
    {
        //TODO
        return null;
    }

    @Override
    public Party getParty(final String name)
    {
        return null;
    }

    @Override
    public void addParty(final Party party)
    {

    }

    @Override
    public Response deleteParty(final String name)
    {
        return null;
    }

    @Override
    public void changeParty(final Party party)
    {

    }

    @Override
    public List<Guest> getPartyGuests(final String name)
    {
        return null;
    }

    @Override
    public List<Item> getPartyItems(final String name)
    {
        return null;
    }

}
