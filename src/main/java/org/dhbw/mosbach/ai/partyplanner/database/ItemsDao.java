package org.dhbw.mosbach.ai.partyplanner.database;

import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Item;

import javax.enterprise.context.Dependent;

@Dependent
public class ItemsDao extends BaseDao<Item,Long,String> {
    @Override
    public Item get(String key) {
        return null;
    }

    @Override
    public boolean add(Item entity) {
        return false;
    }

    @Override
    public boolean change(Item entity) {
        return false;
    }

    @Override
    public boolean delete(Item entity) {
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }
}
