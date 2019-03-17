package org.dhbw.mosbach.ai.partyplanner.partyservice;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.database.dao.PartyDao;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Path("/party")
public class PartyRestService implements IPartyRestService {

    private final Logger logger = Logger.getLogger("root");

    @Inject
    private PartyDao partyDao;

    @Override
    public List<Party> getAllParties() {

        final List<Party> allparties = partyDao.getAll();
        return Lists.newArrayList(allparties);
    }

    @Override
    public Party getParty(final String name) {
        logger.info((String.format("Getting Party '%s'", name)));
        return partyDao.get(name);
    }

    @Override
    public void addParty(final Party party) {
        partyDao.add(party);
    }

    @Override
    public Response deleteParty(final String name) {
        try
        {
            logger.info(String.format("Deleting Party '%s'", name));

           Party party=getParty(name);
            final boolean success = partyDao.delete(party);

            if (success)
                return Response.status(Response.Status.OK).build();
            else
                return Response.serverError().entity("Record not found").build();

        } catch (final Exception e)
        {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public void changeParty(final Party party) {
        partyDao.change(party);
    }

    @Override
    public List<Guest> getPartyGuests(final String name) {
        logger.info(String.format("Getting Party Guests for '%s'", name));
        Party party = partyDao.get(name);
        if(party==null)return null;
        return party.getGuests();
    }

    @Override
    public List<Item> getPartyItems(final String name) {
        logger.info(String.format("Getting Party Guests for '%s'", name));
        Party party = partyDao.get(name);
        List<Item> items=Lists.newArrayList();
        if(party==null)return null;
        for (Guest guest : party.getGuests()) {
            items.addAll(guest.getItems());
        }
        return items;
    }

    @Override
    public Party newParty() {
        return null;
    }

    @Override
    public List<Ingredient> getPartyIngredients(String name) {
        return null;
    }
}
