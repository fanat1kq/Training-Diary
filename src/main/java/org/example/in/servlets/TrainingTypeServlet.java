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
import org.example.in.mappers.TrainingMapper;
import org.example.in.security.Authentication;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;
import org.example.service.ExtraService;
import org.example.service.TrainingService;
import org.example.service.TypeService;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/type/*")
public class TrainingTypeServlet extends HttpServlet {

    private UserService userService;
    private TrainingService trainingService;
    private TypeService typeService;
    private ExtraService extraService;
    private ObjectMapper jacksonMapper;
    private TrainingMapper trainingMapper;
    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
        trainingService = (TrainingService) getServletContext().getAttribute("readingService");
        trainingMapper = (TrainingMapper) getServletContext().getAttribute("trainingMapper");
        typeService = (TypeService) getServletContext().getAttribute("typeService");
        extraService= (ExtraService) getServletContext().getAttribute("extraService");
//        trainingTypeMapper = (TrainingTypeMapper) getServletContext().getAttribute("trainingTypeMapper");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try {
                if (req.getRequestURI().endsWith("/get")) {
                    getTypes(req, resp, authentication);
                }
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

                User user = userService.getByLogin(req.getParameter("login"));
                if (!authentication.getLogin().equals(user.getLogin())) throw new AuthorizeException("Incorrect credentials.");
                String requestURI = req.getRequestURI();//что в юрл запостили
                if (requestURI.endsWith("/add")) {
                    AddTypeRequest typeRequest = jacksonMapper.readValue(inputStream, AddTypeRequest.class);
                    typeService.addType(Type.builder().typeName(typeRequest.type).build());
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("action completed successfully!"));//если удачно
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

    private void requestValidation() throws NotValidParameterException {
//        if (request.getUserName()==null || request.getUserName().isBlank()) {
//            throw new NotValidParameterException("Player login must not be null or empty.");
//        } else if (request.getValue()< 0) {
//            throw new NotValidParameterException("Transaction's amount must not be negative.");
//        }
    }

    private void getTypes(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws NotValidParameterException, IOException {
        String login = req.getParameter("login");
        if (login == null) throw new NotValidParameterException("Login parameter is null!");
        User entity = userService.getByLogin(login);
        if (!authentication.getLogin().equals(entity.getLogin())) throw new AuthorizeException("Incorrect credentials.");

        List<Type> typeHistory = typeService.getAllType();
//        TypeDTO response = new TypeDTO(trainingTypeMapper.toDTOList(typeHistory));

        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), typeHistory);
    }
    private void showCaloriesStatics(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
        String login = req.getParameter("login");
        if (login == null) throw new NotValidParameterException("Login parameter is null!");
        User entity = userService.getByLogin(login);
        if (!authentication.getLogin().equals(entity.getLogin())) throw new AuthorizeException("Incorrect credentials.");
        int caloriesStatics = trainingService.getStatistic();
        CaloriesStaticResponse response = new CaloriesStaticResponse(entity.getLogin(), caloriesStatics);
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), response);
    }
}
