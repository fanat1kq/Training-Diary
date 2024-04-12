package dao;

import org.example.dao.TrainingDAO;
import org.example.models.Extra;
import org.example.models.Training;
import org.example.models.User;
import org.example.models.enumerates.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class TrainingDAOTest {
    TrainingDAO trainingDAO;
    User user;
    Extra extra;
    @BeforeEach
    public void setUp() {
        trainingDAO =new TrainingDAO();
        user = new User();
        extra = new Extra();
    }
    @Test
    public void testGetTraining() {
        user.setName("admin");
        user.setPassword("123");
        user.setRole(Role.ADMIN);
        User user1= user;
        extra.setName("Количество повторений");
        extra.setValue(10);
        HashMap<Training, User> expected = trainingDAO.addTraining(user1,1,100, 300,1,4,2024,extra);
        user.setName("admin");
        user.setPassword("123");
        user.setRole(Role.ADMIN);
        User user2= user;
        HashMap<Training, User> result =  trainingDAO.getTraining(user2,Role.ADMIN);
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void testGetStatistic() {
        user.setName("admin");
        user.setPassword("123");
        user.setRole(Role.ADMIN);
        User user1= user;
        extra.setName("Количество повторений");
        extra.setValue(10);
        trainingDAO.addTraining(user1,1,100, 300,1,4,2024,extra);

        int expected = 300;
        int result =  trainingDAO.getStatistic();
        Assertions.assertEquals(expected, result);
    }
}
