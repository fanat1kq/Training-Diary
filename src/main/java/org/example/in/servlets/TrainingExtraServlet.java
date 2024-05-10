package org.example.in.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.AlreadyExistException;
import org.example.exception.AuthorizeException;
import org.example.exception.NotFoundException;
import org.example.exception.NotValidParameterException;
import org.example.in.dto.*;
import org.example.in.security.Authentication;
import org.example.model.Extra;
import org.example.model.User;
import org.example.service.ExtraService;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/extra")
public class TrainingExtraServlet extends HttpServlet {
    private UserService userService;
    private ExtraService extraService;
    private ObjectMapper jacksonMapper;
    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
        extraService = (ExtraService) getServletContext().getAttribute("extraService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try {
                    getExtra(req, resp, authentication);
            } catch (NotFoundException | NotValidParameterException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (AuthorizeException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (RuntimeException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try(ServletInputStream inputStream = req.getInputStream()) {
                loginValidation(req, authentication);
                AddExtraRequest extraRequest = jacksonMapper.readValue(inputStream, AddExtraRequest.class);
                extraService.addExtra(extraRequest);
                resp.setStatus(HttpServletResponse.SC_OK);
                jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("action completed successfully!"));
            } catch (NotFoundException | AlreadyExistException | JsonParseException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (AuthorizeException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            } catch (RuntimeException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
        }
    }
    private void getExtra(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws NotValidParameterException, IOException {
        String login = req.getParameter("login");
        if (login == null) throw new NotValidParameterException("Login parameter is null!");
        User entity = userService.getByLogin(login);
        if (!authentication.getLogin().equals(entity.getLogin())) throw new AuthorizeException("Incorrect credentials.");
        List<Extra> extra = extraService.getExtra();
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), extra);
    }
    private void loginValidation(HttpServletRequest req, Authentication authentication) {
        String login = req.getParameter("login");
        User user = userService.getByLogin(req.getParameter("login"));
        if (login == null) throw new NotValidParameterException("Login parameter is null!");
        if (!authentication.getLogin().equals(user.getLogin())) throw new AuthorizeException("Incorrect credentials.");
    }
}
