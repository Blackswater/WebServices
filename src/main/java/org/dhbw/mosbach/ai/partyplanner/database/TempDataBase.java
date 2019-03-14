package org.dhbw.mosbach.ai.partyplanner.database;

import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import java.util.List;

public class TempDataBase
{
    private static TempDataBase instance=new TempDataBase();
    private List<Party> parties;
    private List<Guest> guests= Lists.newArrayList();


    public static TempDataBase getInstance()
    {
        return instance;
    }

    public List<Party> getAllPartys()
    {
        return null;
    }
}
