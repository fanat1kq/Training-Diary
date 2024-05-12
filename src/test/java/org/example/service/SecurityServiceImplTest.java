//package org.example.service;
//
//import org.example.model.Users;
//import org.example.model.enumerates.Role;
//import org.example.service.impl.SecurityServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.example.exception.AuthorizeException;
//import org.example.exception.RegisterException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//
///**
// * The type Security service impl test.
// */
//@ExtendWith(MockitoExtension.class)
//class SecurityServiceImplTest {
//
//    @InjectMocks
//    private SecurityServiceImpl securityService;
//    @Mock
//    private SecurityDAO securityDAO;
//    UserDAO userDAO;
//    /**
//     * Test register success.
//     */
//    @Test
//    void testRegister_Success() {
//        String login = "login";
//        String password = "password";
//        Users users = new Users(login, password, Role.ADMIN);
//        Mockito.when(userDAO.findByLogin(login)).thenReturn(null);
//        Mockito.when(securityDAO.save(any(Users.class))).thenReturn(users);
//
//        Users registerUsers = securityService.register(users);
//        assertEquals(login, registerUsers.getLogin());
//        assertEquals(password, registerUsers.getPassword());
//    }
//    /**
//     * Test register throw exception.
//     */
//    @Test
//    void testRegister_ThrowException() {
//        String login = "login";
//        String password = "password";
//        Users users = new Users(login, password, Role.USER);
//        Mockito.when(userDAO.findByLogin(login)).thenReturn(users);
//
//        assertThrows(RegisterException.class, () -> securityService.register(users));
//    }
//
//    /**
//     * Test authorization success.
//     */
//    @Test
//    void testAuthorization_Success() {
//        String login = "login";
//        String password = "password";
//        Users users = new Users(login, password, Role.USER);
//        Mockito.when(userDAO.findByLogin(login)).thenReturn(users);
//
//        securityService.authorization(users);
//    }
//
//    /**
//     * Test authorization throw exception.
//     */
//    @Test
//    void testAuthorization_ThrowException() {
//        String login = "login";
//        String password = "password";
//        Users users = new Users(login, password, Role.USER);
//        Mockito.when(userDAO.findByLogin(login)).thenReturn(null);
//
//        assertThrows(AuthorizeException.class, () -> securityService.authorization(users));
//    }
//}