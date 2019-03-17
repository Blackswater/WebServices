import org.dhbw.mosbach.ai.partyplanner.database.dao.PartyDao;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Tester {



    @Inject
    private PartyDao dao;
    @PersistenceContext
    private EntityManager entityManager;
    @Test
    public void testdao(){
        System.out.println();
    }
}
