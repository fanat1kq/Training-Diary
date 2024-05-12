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

import org.example.in.dto.AddTrainingRequest;
import org.example.in.dto.DeleteTrainingRequest;
import org.example.in.dto.ExceptionResponse;
import org.example.in.dto.UpdateTrainingRequest;
import org.example.in.dto.TrainingHistoryResponse;
import org.example.in.dto.CaloriesStaticResponse;
import org.example.in.dto.SuccessResponse;
import org.example.in.mappers.TrainingMapper;
import org.example.in.security.Authentication;
import org.example.model.Training;
import org.example.model.User;
import org.example.service.TrainingService;
import org.example.service.UserService;

import java.io.IOException;
import java.util.List;

import static org.example.util.urlPath.*;

@WebServlet(TRAINING)
public class TrainingServlet extends HttpServlet {

    private UserService userService;
    private TrainingService trainingService;
    private ObjectMapper jacksonMapper;
    private TrainingMapper trainingMapper;
    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute("userService");
        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
        trainingService = (TrainingService) getServletContext().getAttribute("readingService");
        trainingMapper = (TrainingMapper) getServletContext().getAttribute("trainingMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
        if (authentication.isAuth()) {
            try {
                if (req.getRequestURI().endsWith(HISTORY)) {
                    processTrainingHistory(req, resp, authentication);
                }
                else if (req.getRequestURI().endsWith(CALORIE)) {
                    processCaloriesStatics(req, resp, authentication);
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
                User user = loginValidation(req,authentication);
                String requestURI = req.getRequestURI();
                if (requestURI.endsWith(ADD)) {
                    AddTrainingRequest request = jacksonMapper.readValue(inputStream, AddTrainingRequest.class);//получение с Json(десер-я)
                    trainingService.addTraining(request, user.getId());
                    success(resp);
                } else if (requestURI.endsWith(UPDATE)) {
                    UpdateTrainingRequest updateRequest = jacksonMapper.readValue(inputStream, UpdateTrainingRequest.class);//получение с Json(десер-я)
                    trainingService.updateTraining(user,updateRequest);
                    success(resp);
                } else if (requestURI.endsWith(DELETE)) {
                    DeleteTrainingRequest deleteTrainingRequest = jacksonMapper.readValue(inputStream, DeleteTrainingRequest.class);//получение с Json(десер-я)
                    trainingService.deleteTraining(deleteTrainingRequest.id());
                    success(resp);
                }
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
    private void processTrainingHistory(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws NotValidParameterException, IOException {
        User user = loginValidation(req,authentication);
        List<Training> userHistory = trainingService.getTraining(user.getId(),user.getRole());
        TrainingHistoryResponse response = new TrainingHistoryResponse(user.getLogin(), trainingMapper.toDTOList(userHistory));
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), response);
    }
    private void processCaloriesStatics(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException {
        User user = loginValidation(req,authentication);
        int caloriesStatics = trainingService.getStatistic();
        CaloriesStaticResponse response = new CaloriesStaticResponse(user.getLogin(), caloriesStatics);
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), response);
    }
    private User loginValidation(HttpServletRequest req, Authentication authentication) {
        String login = req.getParameter("login");
        User user = userService.getByLogin(req.getParameter("login"));
        if (login == null) throw new NotValidParameterException("Login parameter is null!");
        if (!authentication.getLogin().equals(user.getLogin())) throw new AuthorizeException("Incorrect credentials.");
        return user;
    }
    private void success(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("action completed successfully!"));
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    public void setJacksonMapper(ObjectMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }

    public void setTrainingMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }
}
