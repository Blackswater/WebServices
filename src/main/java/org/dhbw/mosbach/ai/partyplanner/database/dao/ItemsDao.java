package org.dhbw.mosbach.ai.partyplanner.database.dao;

import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;
import org.dhbw.mosbach.ai.partyplanner.model.Item;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**@author Pascal Röcker
 * first model of a itemdao, things missing
 */
@Dependent
public class ItemsDao extends BaseDao<Item, Long, String> {

    private final Logger logger = Logger.getLogger("root");

    @Override
    public Item get(String name) {
        logger.log(Level.INFO, "Call to getItem({0})", name);

        if (name == null) {
            return null;
        }

        final List<Item> resultList = em.createQuery("from Item a where a.name = :name", Item.class)
                .setParameter("name", name).getResultList();

        return resultList.isEmpty() ? null : resultList.iterator().next();
    }

    @Override
    public boolean add(Item item) {
        logger.log(Level.INFO, "Call to addItem({0})", item);
        try
        {
            //TODO update ingredients
            persist(item);
        }
        catch (final Exception e)
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean change(Item item) {
        logger.log(Level.INFO, "Call to changeIngredient({0})",item);
        Item newIn = null;
        if ((item != null))
        {
            newIn=em.merge(item);
        }
        return (newIn!=null)&&newIn.equals(item);
    }

    @Override
    public boolean delete(Item item) {
        logger.log(Level.INFO, "Call to deleteIngredient({0})", item);
        return (item != null) && deleteById(item.getId());
    }

    @Override
    public boolean deleteByKey(String key) {
        logger.log(Level.INFO, "Call to deleteByKey({0})", key);
        if (key != null)
        {
            final int updateNum = em.createQuery("DELETE FROM Item i WHERE i.name = :name").setParameter("name", key).executeUpdate();
            return updateNum > 0;
        }
        return false;
    }

    public boolean deleteById(long id){
        logger.log(Level.INFO, "Call to deleteByID({0})", id);
        final int updateNum = em.createQuery("DELETE FROM Item i WHERE i.id = :id").setParameter("id", id).executeUpdate();
        return updateNum > 0;
    }

}
