package org.example.dao;//package org.example.dao;
//
//import org.example.containers.PostgresTestContainer;
//import org.example.dao.impl.UserDAOImpl;
//import org.example.dbconfig.ConnectionManager;
//import org.example.liquibase.Liquibase;
//import org.example.model.User;
//import org.example.model.enumerates.Role;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayName("security dao implementation test")
//public class UserDAOImplTest extends PostgresTestContainer{
//    private static UserDAOImpl securityDAO;
//    @BeforeAll
//    public static void setUp() {
//        ConnectionManager connectionManager = new ConnectionManager(
//                container.getJdbcUrl(),
//                container.getUsername(),
//                container.getPassword()
//        );
//        Liquibase liquibase=new Liquibase(connectionManager);
//        liquibase.start();
//
//        securityDAO = new UserDAOImpl(connectionManager);
//    }
//    @Test
//    @DisplayName("save method verification test")
//    public void testSave() {
//        User userToSave = new User("Alice","password",Role.USER);
//
//        User savedUser = securityDAO.save(userToSave);
//        assertEquals(userToSave.getLogin(), savedUser.getLogin());
//        assertEquals(userToSave.getPassword(), savedUser.getPassword());
//        assertEquals(userToSave.getRole(), savedUser.getRole());
//    }
//
//    @Test
//    @DisplayName("find by login method verification test")
//    public void testFindByLogin() {
//        User user = User.builder().login("Bob").password("password").role(Role.USER).build();
//        securityDAO.save(user);
//
//        User foundUser = securityDAO.findByLogin("Bob");
//        assertNotNull(foundUser);
//        assertEquals("Bob", foundUser.getLogin());
//
//        User notFoundUser = securityDAO.findByLogin("NonExistentLogin");
//        assertNull(notFoundUser);
//    }
//
//}