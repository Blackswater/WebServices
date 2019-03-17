package org.dhbw.mosbach.ai.partyplanner.database.dao;

import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngredientDao extends BaseDao<Ingredient, Long, String> {
    private final Logger logger = Logger.getLogger("root");

    @Override
    public Ingredient get(String name) {
        logger.log(Level.INFO, "Call to getIngredient({0})", name);

        if (name == null) {
            return null;
        }

        final List<Ingredient> resultList = em.createQuery("from Ingredient a where a.name = :name", Ingredient.class)
                .setParameter("name", name).getResultList();

        return resultList.isEmpty() ? null : resultList.iterator().next();
    }

    @Override
    public boolean add(Ingredient ingredient) {
        logger.log(Level.INFO, "Call to addIngredient({0})", ingredient);
        try
        {
            persist(ingredient);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean change(Ingredient ingredient) {
        logger.log(Level.INFO, "Call to changeIngredient({0})",ingredient);
        Ingredient newIn = null;
        if ((ingredient != null))
        {
            newIn=em.merge(ingredient);
        }
        return (newIn!=null)&&newIn.equals(ingredient);
    }

    @Override
    public boolean delete(Ingredient ingredient) {
        logger.log(Level.INFO, "Call to deleteIngredient({0})", ingredient);
        return (ingredient != null) && deleteById(ingredient.getId());
    }

    @Override
    public boolean deleteByKey(String key) {
        logger.log(Level.INFO, "Call to deleteByKey({0})", key);

        if (key != null)
        {
            final int updateNum = em.createQuery("DELETE FROM Ingredient i WHERE i.name = :name").setParameter("name", key).executeUpdate();
            return updateNum > 0;
        }
        return false;
    }

    public boolean deleteById(long id){
        logger.log(Level.INFO, "Call to deleteByID({0})", id);
            final int updateNum = em.createQuery("DELETE FROM Ingredient i WHERE i.id = :id").setParameter("id", id).executeUpdate();
            return updateNum > 0;
    }
}
