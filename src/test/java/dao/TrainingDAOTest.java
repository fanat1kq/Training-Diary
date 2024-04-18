package dao;

import org.example.dao.impl.TrainingDAOImpl;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

public class TrainingDAOTest {
    TrainingDAOImpl trainingDAO;
    User user;
    Extra extra;
    @BeforeEach
    public void setUp() {
        trainingDAO =new TrainingDAOImpl();
        user = new User();
        extra = new Extra();
    }
    @Test
    public void testGetTraining() {
//        user.setLogin("admin");
//        user.setPassword("123");
//        user.setRole(Role.ADMIN);
//        User user1= user;
//        extra.setName("Количество повторений");
//        extra.setValue(10);
//        Training training = new Training("подтягивания",
//                300,1, LocalDate.of(2024,4,1),extra);
//        Map<Training, User> expected = trainingDAO.addTraining(user1,training);
//
//        user.setLogin("admin");
//        user.setPassword("123");
//        user.setRole(Role.ADMIN);
//        User user2= user;
//        Map<Training, User> result =  trainingDAO.getTraining(user2);
//        Assertions.assertEquals(expected, result);
    }
    @Test
    public void testGetStatistic() {
//        user.setLogin("admin");
//        user.setPassword("123");
//        user.setRole(Role.ADMIN);
//        User user1= user;
//        extra.setName("Количество повторений");
//        extra.setValue(10);
//        Training training = new Training("подтягивания",
//                300,1, LocalDate.of(2024,4,1),extra);
//        trainingDAO.addTraining(user1,training);
//
//        int expected = 300;
//        int result =  trainingDAO.getStatistic();
//        Assertions.assertEquals(expected, result);
    }
}
