package org.example.service;

import org.example.dao.UserDAO;
import org.example.in.dto.JwtResponse;
import org.example.model.enumerates.Role;
import org.example.service.impl.SecurityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.dao.SecurityDAO;
import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.example.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

/**
 * The type Security service impl test.
 */
@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

    @InjectMocks
    private SecurityServiceImpl securityService;
    @Mock
    private SecurityDAO securityDAO;
    UserDAO userDAO;
    /**
     * Test register success.
     */
    @Test
    void testRegister_Success() {
        String login = "login";
        String password = "password";
        User user = new User(login, password, Role.ADMIN);
        Mockito.when(userDAO.findByLogin(login)).thenReturn(null);
        Mockito.when(securityDAO.save(any(User.class))).thenReturn(user);

        User registerUser = securityService.register(user);
        assertEquals(login,registerUser.getLogin());
        assertEquals(password, registerUser.getPassword());
    }
    /**
     * Test register throw exception.
     */
    @Test
    void testRegister_ThrowException() {
        String login = "login";
        String password = "password";
        User user = new User(login, password, Role.USER);
        Mockito.when(userDAO.findByLogin(login)).thenReturn(user);

        assertThrows(RegisterException.class, () -> securityService.register(user));
    }

    /**
     * Test authorization success.
     */
    @Test
    void testAuthorization_Success() {
        String login = "login";
        String password = "password";
        User user = new User(login, password, Role.USER);
        Mockito.when(userDAO.findByLogin(login)).thenReturn(user);

        securityService.authorization(user);
    }

    /**
     * Test authorization throw exception.
     */
    @Test
    void testAuthorization_ThrowException() {
        String login = "login";
        String password = "password";
        User user = new User(login, password, Role.USER);
        Mockito.when(userDAO.findByLogin(login)).thenReturn(null);

        assertThrows(AuthorizeException.class, () -> securityService.authorization(user));
    }
}