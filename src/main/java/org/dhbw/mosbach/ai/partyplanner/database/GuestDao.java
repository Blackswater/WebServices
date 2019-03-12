package org.dhbw.mosbach.ai.partyplanner.database;

import com.google.common.collect.ImmutableList;
import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Guest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//TODO
public class GuestDao extends BaseDao<Guest,Long,String> {

    private static final long serialVersionUID = -5385253294954522663L;
    private final Logger logger = Logger.getLogger("root");

    public GuestDao() {
        super();
    }
    @Override
    public List<Guest> getAll()
    {
        logger.log(Level.INFO, "Call to getAllGuests");
        return ImmutableList.copyOf(super.getAll());
    }

    @Override
    public Guest get(String key) {
        logger.log(Level.INFO, "Call to getArticle({0})", key);

        if (key == null)
        {
            return null;
        }

        final List<Guest> resultList = em.createQuery("from Guest a where a.name = :name", Guest.class)
                .setParameter("name", key).getResultList();

        return resultList.isEmpty() ? null : resultList.iterator().next();
    }

    @Transactional
    @Override
    public boolean add(Guest entity) {
        return false;
    }
    @Transactional
    @Override
    public boolean change(Guest guest) {

            logger.log(Level.INFO, "Call to changeArticle({0})", guest);

            // TODO interface is broken because the name of the article cannot be
            // changed in that way!

            if ((guest != null) && (guest.getName() != null))
            {
                // final Article managedArticle = get(article.getName());

                // TODO dirty hack
                delete(guest);
                add(guest);
            }

            return true;
    }
    @Transactional
    @Override
    public boolean delete(Guest guest) {
        logger.log(Level.INFO, "Call to deleteArticle({0})", guest);

        return (guest != null) && deleteByKey(guest.getName());
    }
    @Transactional
    @Override
    public boolean deleteByKey(String key) {
        logger.log(Level.INFO, "Call to deleteByKey({0})", key);
        //TODO
        if (key != null)
        {
            em.createQuery("DELETE FROM Review r WHERE r.article.name = :name").setParameter("name", key).executeUpdate();

            final int updateNum = em.createQuery("DELETE FROM Article a WHERE a.name = :name").setParameter("name", key)
                    .executeUpdate();

            return updateNum > 0;
        }

        return false;
    }
}
