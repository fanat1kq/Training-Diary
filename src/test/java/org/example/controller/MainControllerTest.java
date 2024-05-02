package org.example.controller;

import org.example.exception.NotValidParametrException;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.example.service.SecurityService;
import org.example.service.TrainingService;
import org.example.service.TypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


/**
 * The type Main controller test.
 */
@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private TrainingService trainingService;
    @Mock
    private SecurityService securityService;
    @Mock
    private TypeService typeService;

    /**
     * Test register success.
     */
    @Test
    void testRegister_Success() {
        String login = "login";
        String password = "password";
        User user = new User(login,password,Role.ADMIN);

        User expected = new User("login","password",Role.ADMIN);
        Mockito.when(securityService.createUser(user)).thenReturn(expected);

        User result = mainController.createUser(user);
        assertEquals(expected, result);
    }

    /**
     * Test register invalid arguments.
     */
    @Test
    void testRegister_InvalidArguments() {
        String login = "";
        String password = "password";

        assertThrows(NotValidParametrException.class, () -> mainController.createUser(new User(login, password,Role.ADMIN)));
    }

    /**
     * Test authorize success.
     */
    @Test
    void testAuthorize_Success() {
        String login = "login";
        String password = "password";

        User expected = new User(login, password,Role.ADMIN);
        Mockito.when(securityService.login(new User(login, password,Role.ADMIN))).thenReturn(expected);

        User result = mainController.login(new User(login, password,Role.ADMIN));

        assertEquals(expected, result);
    }

    /**
     * Test get training success.
     */
    @Test
    void testGetTraining_Success() {
        mainController.getTraining(new User());
        Mockito.verify(trainingService, Mockito.times(1)).getTraining(anyInt(),any());
    }
    /**
     * Test add training success.
     */
    @Test
    void testAddTraining_Success() {
        Training training = new Training(1,1,1, LocalDate.of(2024,4,10),123,1,1);
        mainController.addTraining(training);
        Mockito.verify(trainingService, Mockito.times(1)).addTraining(training);
    }

    /**
     * Test get static success.
     */
    @Test
    void testGetStatic_Success() {
        Training training = new Training(1,1,1, LocalDate.of(2024,4,10),123,1,1);
        mainController.addTraining(training);
        mainController.getStatistic();
        Mockito.verify(trainingService, Mockito.times(1)).getStatistic();
    }
    /**
     * Test add type training success.
     */
    @Test
    void testAddType_Success() {
        Type type = new Type(1,"type");
        mainController.addType(type);
        Mockito.verify(typeService, Mockito.times(1)).addType(type);
    }

    /**
     * Test delete training success.
     */
    @Test
    void testDeleteTraining_Success() {
        int id =1;
        Training training = new Training(1,1,1, LocalDate.of(2024,4,10),123,1,1);
        mainController.addTraining(training);
        mainController.deleteTraining(id);
        Mockito.verify(trainingService, Mockito.times(1)).deleteTraining(id);
    }
    /**
     * Test update training success.
     */
    @Test
    void testUpdateTraining_Success() {
        Training training = new Training(1,1,1, LocalDate.of(2024,4,10),123,1,1);
        mainController.addTraining(training);
        User user = new User("login","password",Role.ADMIN);
        mainController.updateTraining(user, training);
        Mockito.verify(trainingService, Mockito.times(1)).updateTraining(user, training);
    }
}