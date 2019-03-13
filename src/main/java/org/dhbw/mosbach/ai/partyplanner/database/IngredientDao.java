package org.dhbw.mosbach.ai.partyplanner.database;

import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;

public class IngredientDao extends BaseDao<Ingredient,Long,String> {
    @Override
    public Ingredient get(String key) {
        return null;
    }

    @Override
    public boolean add(Ingredient entity) {
        return false;
    }

    @Override
    public boolean change(Ingredient entity) {
        return false;
    }

    @Override
    public boolean delete(Ingredient entity) {
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        return false;
    }
}
