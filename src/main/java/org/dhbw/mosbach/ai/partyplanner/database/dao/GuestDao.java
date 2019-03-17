package org.dhbw.mosbach.ai.partyplanner.database.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;
import org.dhbw.mosbach.ai.partyplanner.model.Ingredient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//TODO

@Dependent
public class GuestDao extends BaseDao<Guest,Long,String> {

    private static final long serialVersionUID = -5385253294954522663L;
    private final Logger logger = Logger.getLogger("root");

    public GuestDao()
    {
        super();
    }

    @Override
    public List<Guest> getAll()
    {
        logger.log(Level.INFO, "Call to getAllGuests");

        return ImmutableList.copyOf(super.getAll());
    }

    @Override
    public Guest get(String name)
    {
        logger.log(Level.INFO, "Call to getGuest({0})", name);

        if (name == null)
        {
            return null;
        }

        final List<Guest> resultList = em.createQuery("from guests a where a.name = :name", Guest.class)
                .setParameter("name", name).getResultList();

        return resultList.isEmpty() ? null : resultList.iterator().next();
    }

    @Override
    @Transactional
    public boolean add(final Guest Guest)
    {
        logger.log(Level.INFO, "Call to addGuest({0})", Guest);

        // using a lambda expression
        try
        {
           //TODO update
            persist(Guest);
        }
        catch (final Exception e)
        {
            return false;
        }

        return true;
    }
    @Override
    @Transactional
    public boolean delete(final Guest Guest)
    {
        logger.log(Level.INFO, "Call to deleteGuest({0})", Guest);

        return (Guest != null) && deleteByKey(Guest.getName());
    }

    @Override
    @Transactional
    public boolean deleteByKey(String key)
    {
        logger.log(Level.INFO, "Call to deleteByKey({0})", key);

        if (key != null)
        {
            em.createQuery("DELETE FROM Review r WHERE r.Guest.name = :name").setParameter("name", key).executeUpdate();
            final int updateNum = em.createQuery("DELETE FROM Guest a WHERE a.name = :name").setParameter("name", key)
                    .executeUpdate();

            return updateNum > 0;
        }

        return false;
    }

    @Override
    @Transactional
    public boolean change(final Guest Guest)
    {
        logger.log(Level.INFO, "Call to changeGuest({0})", Guest);

        // TODO interface is broken because the name of the Guest cannot be
        // changed in that way!
        if ((Guest != null) && (Guest.getName() != null))
        {
            // final Guest managedGuest = get(Guest.getName());
            // TODO dirty hack
            delete(Guest);
            add(Guest);
        }

        return true;
    }
}
