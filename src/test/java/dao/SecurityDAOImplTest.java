package dao;

import org.example.dao.impl.SecurityDAOImpl;
import org.example.model.User;
import org.example.model.enumerates.Role;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityDAOImplTest {
    private SecurityDAOImpl securityDAO;
    static private User user1;
    static private User user2;
    static private User user3;
    @BeforeAll
    static void init() {
        user1 = new User("qwe", "qwe", Role.USER);
        user2 = new User("asd", "asd", Role.ADMIN);
        user3 = new User("zxc", "zxc", Role.ADMIN);
    }

    @BeforeEach
    void refresh() {
        securityDAO = new SecurityDAOImpl();
        securityDAO.createUser(user1);
        securityDAO.createUser(user2);
        securityDAO.createUser(user3);
    }
    @AfterEach
    void tearDown() {
        securityDAO = null;
    }

    @Test
    void testFindById_Success() {
        User result= securityDAO.findByLogin("qwe");

        User expected = user1;
        assertEquals(result.getLogin(), expected.getLogin());
        assertEquals(result.getPassword(), expected.getPassword());
        assertEquals(result.getRole(), expected.getRole());
    }
    @Test
    void testSave() {
        User newPlayer = new User("aboba", "7777", Role.USER);
        User result = securityDAO.createUser(newPlayer);

        assertEquals(newPlayer.getLogin(), result.getLogin());
        assertEquals(newPlayer.getPassword(), result.getPassword());
        assertEquals(newPlayer.getRole(), result.getRole());
    }
}