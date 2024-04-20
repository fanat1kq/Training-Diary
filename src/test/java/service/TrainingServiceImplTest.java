package service;

import org.example.dao.TrainingDAO;
import org.example.exception.AlreadyExistException;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.enumerates.Role;
import org.example.service.impl.TrainingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

    @InjectMocks
    private TrainingServiceImpl trainingService;
    @Mock
    private TrainingDAO trainingDAO;
    static private Training training1;
    static private Training training2;

    /**
     * Init.
     */
    @BeforeAll
    static void init() {
        training1 = new Training(1,1, "type",123,123, LocalDate.of(2024,4,10),new Extra("name",11));
        training2 = new Training(2,1, "type2",456,456, LocalDate.of(2022,4,10),new Extra("name2",22));
    }

    /**
     * Test get user history.
     */
    @Test
    void testGetTraining() {
        List<Training> trainings = List.of(
                training1, training2
        );

        Mockito.when(trainingDAO.findAllByUserId(1)).thenReturn(trainings);
        List<Training> trainingHistory = trainingService.getTraining(1, Role.USER);
        assertIterableEquals(trainings, trainingHistory);
    }

    /**
     * Test add training success.
     */
    @Test
    void testAddTraining_Success() {
        LocalDate date = LocalDate.of(2024,4,10);
        String type = "type";
        Mockito.when(trainingDAO.findByDate(date, type)).thenReturn(null);

        trainingService.addTraining(training1);
        Mockito.verify(trainingDAO, Mockito.times(1)).addTraining(any());
    }

    /**
     * Test  failed add training.
     */
    @Test
    void testAddTraining_Failed() {
        LocalDate date = LocalDate.of(2024,4,10);
        String type = "type";
        Mockito.when(trainingDAO.findByDate(date, type)).thenReturn(training1);

        assertThrows(AlreadyExistException.class, () -> trainingService.addTraining(training1));
    }

}