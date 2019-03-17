package org.dhbw.mosbach.ai.partyplanner.partyservice;

import org.dhbw.mosbach.ai.partyplanner.database.TempDataBase;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.ws.rs.core.Response;
import java.util.logging.Logger;

public class GuestRestService implements IGuestRestService {

    private TempDataBase db = TempDataBase.getInstance();
    private final Logger logger = Logger.getLogger("root");
    @Override
    public Guest getGuest(final String code) {
        return db.getGuest(code);
    }

    @Override
    public Response deleteGuest(final String code) {
        try {
            logger.info(String.format("Deleting Party '%s'", code));

            Guest guest = getGuest(code);
            final boolean success = db.deleteGuest(guest);

            if (success)
                return Response.status(Response.Status.OK).build();
            else
                return Response.serverError().entity("Record not found").build();

        } catch (final Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @Override
    public void changeGuest(final Guest guest) {
        db.updateGuest(guest);
    }


}
