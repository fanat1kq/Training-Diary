package dao;

import org.example.containers.PostgresTestContainer;
import org.example.dao.impl.TrainingDAOImpl;
import org.example.dbconfig.ConnectionManager;
import org.example.liquibase.Liquibase;
import org.example.model.Training;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingDAOImplTest {
    static TrainingDAOImpl trainingDAO;

    /**
     * Init.
     */
    @BeforeAll
    public static void setUp() {
        ConnectionManager connectionManager = new ConnectionManager(
                PostgresTestContainer.container.getJdbcUrl(),
                PostgresTestContainer.container.getUsername(),
                PostgresTestContainer.container.getPassword()
        );
        Liquibase liquibase=new Liquibase();
        liquibase.start();

        trainingDAO = new TrainingDAOImpl(connectionManager);
    }

    /**
     * Refresh.
     */

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
        trainingDAO = null;
    }

//    @Test
//    public void testFindByDate_Empty() {
//        LocalDate date = LocalDate.of(2024,2,1);
//        String type = "бег";
//        Training find = trainingDAO.findByDate(date,type);
//
//        assertNull(find);
//    }
//    @Test
//    public void testFindByDate_Success() {
//        LocalDate date = LocalDate.of(2024,3,1);
//        String type = "бег";
//        Training find = trainingDAO.findByDate(date,type);
//
//        assertNotNull(find);
//        assertEquals(training.getDate(), find.getDate());
//        assertEquals(training.getId(), find.getId());
//        assertEquals(training.getType(), find.getType());
//        assertEquals(training.getUserId(), find.getUserId());
//        assertEquals(training.getCalorie(), find.getCalorie());
//        assertEquals(training.getTime(), find.getTime());
//        assertEquals(training.getExtra().getName(), find.getExtra().getName());
//        assertEquals(training.getExtra().getValue(), find.getExtra().getValue());
//
//    }
    @Test
    void findAllByUserId() {
        List<Training> all = trainingDAO.findAllByUserId(1);
        assertEquals(2, all.size());
    }
    @Test
    void findAllByUserId_Empty() {
        List<Training> all = trainingDAO.findAllByUserId(2);
        assertEquals(0, all.size());
    }
    @Test
    void findAll() {
        List<Training> all = trainingDAO.findAll();
        assertEquals(2, all.size());
    }
}
