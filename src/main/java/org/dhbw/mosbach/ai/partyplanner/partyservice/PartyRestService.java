package org.dhbw.mosbach.ai.partyplanner.partyservice;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.database.TempDataBase;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;
import org.dhbw.mosbach.ai.partyplanner.model.Party;
import org.dhbw.mosbach.ai.partyplanner.reducer.IngredientReducer;
import org.dhbw.mosbach.ai.partyplanner.reducer.ItemListReducer;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/party")
public class PartyRestService implements IPartyRestService {

    private final Logger logger = Logger.getLogger("root");

    private TempDataBase db = TempDataBase.getInstance();

    @Override
    public List<Party> getAllParties() {

        final List<Party> allparties = db.getAllPartys();
        return Lists.newArrayList(allparties);
    }

    @Override
    public Party getParty(final String name) {
        logger.info((String.format("Getting Party '%s'", name)));
        return db.getParty(name);
    }

    @Override
    public Party newParty() {
        Party newParty=new Party();
        db.addParty(newParty);
        return newParty;
    }

    @Override
    public void addParty(final Party party) {
        db.addParty(party);
    }

    @Override
    public Response deleteParty(final String name) {
        try {
            logger.info(String.format("Deleting Party '%s'", name));

            Party party = getParty(name);
            final boolean success = db.deleteParty(party);

            if (success)
                return Response.status(Response.Status.OK).build();
            else
                return Response.serverError().entity("Record not found").build();

        } catch (final Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public void changeParty(final Party party) {
        db.changeParty(party);
    }

    @Override
    public List<Guest> getPartyGuests(final String name) {
        logger.info(String.format("Getting Party Guests for '%s'", name));
        Party party = db.getParty(name);
        if (party == null) return null;
        return party.getGuests();
    }

    @Override
    public List<Item> getPartyItems(final String name) {
        logger.info(String.format("Getting Party Guests for '%s'", name));
        Party party = db.getParty(name);
        List<Item> items = Lists.newArrayList();
        if (party == null) return null;
        for (Guest guest : party.getGuests()) items.addAll(guest.getItems());
        ItemListReducer reducer = new ItemListReducer();
        return reducer.reduce(items);
    }

    @Override
    public List<Ingredient> getPartyIngredients(String name) {
        logger.info(String.format("Getting Party Ingredients for '%s'", name));
        Party party = db.getParty(name);
        List<Ingredient> ingredients = Lists.newArrayList();
        if (party == null) return null;
        for (Guest guest : party.getGuests())
            if (guest.getItems() != null) for (Item item : guest.getItems())
                ingredients.addAll(item.getIngredients());
        IngredientReducer reducer = new IngredientReducer();
        return  reducer.reduce(ingredients);
    }

}
