package dao;

import org.example.dao.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void registerUser_ValidUserSuccess() throws ParseException {
        userDAO.createUser("testUser", "password", "USER");

        boolean isGood = userDAO.login("testUser", "password");
        Assertions.assertTrue(isGood);

    }
}