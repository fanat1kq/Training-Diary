package org.example.dao;

import org.example.containers.PostgresTestContainer;
import org.example.dao.impl.TrainingDAOImpl;
import org.example.dbconfig.ConnectionManager;
import org.example.liquibase.Liquibase;
import org.example.model.Training;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TrainingDAOImplTest extends PostgresTestContainer{
    static TrainingDAOImpl trainingDAO;

    /**
     * Init.
     */
    @BeforeAll
    public static void setUp() {
        ConnectionManager connectionManager = new ConnectionManager(
                container.getJdbcUrl(),
                container.getUsername(),
                container.getPassword()
        );
        Liquibase liquibase=new Liquibase(connectionManager);
        liquibase.start();

        trainingDAO = new TrainingDAOImpl(connectionManager);
    }

    @Test
    @DisplayName("find by date is empty method verification test")
    public void testFindByDate_Empty() {
        LocalDate date = LocalDate.of(2024,2,1);
        int typeId = 1;
        Training training = Training.builder()
                .date(LocalDate.of(2024,4,1))
                .calorie(1)
                .time(1)
                .id(1)
                .userId(1)
                .id(1)
                .typeId(1)
                .extraId(1)
                .build();
        trainingDAO.save(training);

        Training find = trainingDAO.findByDate(date,typeId);

        Assertions.assertNull(find);
    }
    @Test
    @DisplayName("find by date method verification test")
    public void testFindByDate_Success() {
        LocalDate date = LocalDate.of(2024,2,1);
        int typeId = 1;
        Training training = Training.builder()
                .date(LocalDate.of(2024,2,1))
                .calorie(1)
                .time(1)
                .id(1)
                .userId(1)
                .id(1)
                .typeId(1)
                .extraId(1)
                .build();
        trainingDAO.save(training);

        Training find = trainingDAO.findByDate(date,typeId);

        Assertions.assertNotNull(find);
        assertEquals(training.getDate(), find.getDate());
        assertEquals(training.getId(), find.getId());
        assertEquals(training.getTypeId(), find.getTypeId());
        assertEquals(training.getUserId(), find.getUserId());
        assertEquals(training.getCalorie(), find.getCalorie());
        assertEquals(training.getTime(), find.getTime());
        assertEquals(training.getExtraId(), find.getExtraId());
    }
    @Test
    @DisplayName("find all by user id method verification test")
    void findAllByUserId() {
        Training training1 = Training.builder()
                .date(LocalDate.of(2024,4,1))
                .calorie(1)
                .time(1)
                .id(1)
                .userId(1)
                .id(1)
                .typeId(1)
                .extraId(1)
                .build();
        Training training2 = Training.builder()
                .date(LocalDate.of(2024,5,1))
                .calorie(2)
                .time(2)
                .id(2)
                .userId(2)
                .id(2)
                .typeId(2)
                .extraId(2)
                .build();
        trainingDAO.save(training1);
        trainingDAO.save(training2);

        List<Training> all = trainingDAO.findAllByUserId(1);
        assertEquals(1, all.size());
    }
    @Test
    @DisplayName("find all by user id is empty method verification test")
    void findAllByUserId_Empty() {
        Training training1 = Training.builder()
                .date(LocalDate.of(2024,4,1))
                .calorie(1)
                .time(1)
                .id(1)
                .userId(1)
                .id(1)
                .typeId(1)
                .extraId(1)
                .build();
        Training training2 = Training.builder()
                .date(LocalDate.of(2024,5,1))
                .calorie(2)
                .time(2)
                .id(2)
                .userId(2)
                .id(2)
                .typeId(2)
                .extraId(2)
                .build();
        trainingDAO.save(training1);
        trainingDAO.save(training2);

        List<Training> all = trainingDAO.findAllByUserId(3);
        assertEquals(0, all.size());
    }
    @Test
    @DisplayName("find all method verification test")
    void findAll() {
        Training training1 = Training.builder()
                .date(LocalDate.of(2024,4,1))
                .calorie(1)
                .time(1)
                .id(1)
                .userId(1)
                .id(1)
                .typeId(1)
                .extraId(1)
                .build();
        Training training2 = Training.builder()
                .date(LocalDate.of(2024,5,1))
                .calorie(2)
                .time(2)
                .id(2)
                .userId(2)
                .id(2)
                .typeId(2)
                .extraId(2)
                .build();
        trainingDAO.save(training1);
        trainingDAO.save(training2);

        List<Training> trainings= trainingDAO.findAll();
        assertFalse(trainings.isEmpty());
        assertEquals(5, trainings.size());
    }
}
