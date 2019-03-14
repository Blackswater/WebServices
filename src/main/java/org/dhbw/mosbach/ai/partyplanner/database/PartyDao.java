package org.dhbw.mosbach.ai.partyplanner.database;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.dhbw.mosbach.ai.partyplanner.db.BaseDao;
import org.dhbw.mosbach.ai.partyplanner.model.Party;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class PartyDao extends BaseDao<Party, Long, String> {

    private static final long serialVersionUID = 7535800010653286921L;

    private final Logger logger = Logger.getLogger("root");

    @Inject
    private GuestDao guestDao;

    @Inject
    private ItemsDao itemsDao;

    public PartyDao() {
        super();
    }

    @Override
    public List<Party> getAll() {
        logger.log(Level.INFO, "Call to getAllPartys");

        return ImmutableList.copyOf(super.getAll());
    }

    @Override
    public Party get(String name) {
        logger.log(Level.INFO, "Call to getParty({0})", name);

        if (name == null) {
            return null;
        }

        final List<Party> resultList = em.createQuery("from Party a where a.name = :name", Party.class)
                .setParameter("name", name).getResultList();

        return resultList.isEmpty() ? null : resultList.iterator().next();
    }

    @Override
    @Transactional
    public boolean add(final Party Party) {
        logger.log(Level.INFO, "Call to addParty({0})", Party);
        //TODO
        return true;
    }


    @Transactional
    public void deleteAll() {
        logger.log(Level.INFO, "Call to deleteAll()");
        em.createQuery("DELETE FROM Party a").executeUpdate();
    }

    @Override
    @Transactional
    public boolean delete(final Party Party) {
        logger.log(Level.INFO, "Call to deleteParty({0})", Party);

        return (Party != null) && deleteByKey(Party.getName());
    }

    @Override
    @Transactional
    public boolean deleteByKey(String key) {
        logger.log(Level.INFO, "Call to deleteByKey({0})", key);

        //TODO
        return false;
    }

    @Override
    @Transactional
    public boolean change(final Party Party) {
        logger.log(Level.INFO, "Call to changeParty({0})", Party);

        if ((Party != null) && (Party.getName() != null)) {
            // final Party managedParty = get(Party.getName());

            // TODO dirty hack
            delete(Party);
            add(Party);
        }

        return true;
    }

}
