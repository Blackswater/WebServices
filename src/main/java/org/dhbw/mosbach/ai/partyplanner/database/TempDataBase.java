package org.dhbw.mosbach.ai.partyplanner.database;

import com.google.common.collect.Maps;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**@author Pascal Röcker
 * "db" just to test the rest service , shoud be replaced with DAO, if driver is ready
 */

public class TempDataBase
{
    private static TempDataBase instance = new TempDataBase();
    private HashMap<String, Party> parties = Maps.newHashMap();
    private HashMap<String, Guest> guests = Maps.newHashMap();

    public static TempDataBase getInstance()
    {
        return instance;
    }

    public List<Party> getAllPartys()
    {
        return parties.values().stream().sorted().collect(Collectors.toList());
    }

    public Party getParty(String name)
    {
        return parties.get(name);
    }

    public boolean deleteParty(Party party)
    {
        return parties.remove(party.getName()) != null;
    }

    public void addParty(Party party)
    {
        parties.put(party.getName(), party);
    }

    public void changeParty(Party party)
    {
        parties.replace(party.getName(), party);
    }

    public Guest getGuest(String code)
    {
        return guests.get(code);
    }

    public boolean deleteGuest(Guest guest)
    {
        return guests.remove(guest.getCode()) != null;
    }

    public void updateGuest(Guest guest)
    {
        guests.replace(guest.getCode(), guest);
    }
}
