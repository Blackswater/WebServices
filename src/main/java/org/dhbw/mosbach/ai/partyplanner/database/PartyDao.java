package org.dhbw.mosbach.ai.partyplanner.database;

import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

public class PartyDao extends BaseDao<Party,Long,String> {
    @Override
    public Party get(String key) {
        return null;
    }

    @Override
    public boolean add(Party entity) {
        return false;
    }

    @Override
    public boolean change(Party entity) {
        return false;
    }

    @Override
    public boolean delete(Party entity) {
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }
}
