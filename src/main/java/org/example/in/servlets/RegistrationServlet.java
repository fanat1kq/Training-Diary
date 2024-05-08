package org.example.in.servlets;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exception.RegisterException;
import org.example.in.dto.ExceptionResponse;
import org.example.in.dto.SecurityDTO;
import org.example.in.dto.SuccessResponse;
import org.example.model.User;
import org.example.service.SecurityService;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private SecurityService securityService;
    private ObjectMapper jacksonMapper;
    @Override
    public void init() {
        securityService = (SecurityService) getServletContext().getAttribute("securityService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try(ServletInputStream inputStream = req.getInputStream()) {
            SecurityDTO securityDTO = jacksonMapper.readValue(inputStream, SecurityDTO.class);
            User registered = securityService.register(new User(securityDTO.login(), securityDTO.password(),securityDTO.role()));

            resp.setStatus(HttpServletResponse.SC_CREATED);
            jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("User with login " + registered.getLogin() + " successfully created."));
        } catch (RegisterException | JsonParseException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
        }
    }

}
