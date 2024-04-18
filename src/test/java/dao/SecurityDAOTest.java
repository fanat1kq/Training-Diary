package dao;

import org.example.dao.impl.SecurityDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class SecurityDAOTest {
    private SecurityDAOImpl userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new SecurityDAOImpl();
    }

    @Test
    void registerUser_ValidUserSuccess() throws ParseException {
//        userDAO.createUser("testUser", "password", "USER");
//
//        boolean isGood = userDAO.login("testUser", "password");
//        Assertions.assertTrue(isGood);

    }
}