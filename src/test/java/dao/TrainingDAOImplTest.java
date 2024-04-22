package dao;

import org.example.dao.impl.TrainingDAOImpl;
import org.example.model.Extra;
import org.example.model.Training;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingDAOImplTest {
    TrainingDAOImpl trainingDAO;
    static private Training training;
    static private Training training2;

    /**
     * Init.
     */
    @BeforeAll
    static void init() {
        training = new Training(1,1, "бег",123,123, LocalDate.of(2024,3,1),new Extra("name",11));
        training2 = new Training(1,1, "бег",123,123, LocalDate.of(2024,4,1),new Extra("name",11));
    }

    /**
     * Refresh.
     */
    @BeforeEach
    void refresh() {
        trainingDAO = new TrainingDAOImpl();
        trainingDAO.addTraining(training);
        trainingDAO.addTraining(training2);
    }

    /**
     * Tear down.
     */
    @AfterEach
    void tearDown() {
        trainingDAO = null;
    }

    @Test
    public void testFindByDate_Empty() {
        LocalDate date = LocalDate.of(2024,2,1);
        String type = "бег";
        Training find = trainingDAO.findByDate(date,type);

        assertNull(find);
    }
    @Test
    public void testFindByDate_Success() {
        LocalDate date = LocalDate.of(2024,3,1);
        String type = "бег";
        Training find = trainingDAO.findByDate(date,type);

        assertNotNull(find);
        assertEquals(training.getDate(), find.getDate());
        assertEquals(training.getId(), find.getId());
        assertEquals(training.getType(), find.getType());
        assertEquals(training.getUserId(), find.getUserId());
        assertEquals(training.getCalorie(), find.getCalorie());
        assertEquals(training.getTime(), find.getTime());
        assertEquals(training.getExtra().getName(), find.getExtra().getName());
        assertEquals(training.getExtra().getValue(), find.getExtra().getValue());

    }
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
