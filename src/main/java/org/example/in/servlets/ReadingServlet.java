package org.example.in.servlets;//package org.example.in.servlets;
//
//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.exception.AuthorizeException;
//import org.example.exception.ValidationParametersException;
//import org.example.in.dto.ExceptionResponse;
//import org.example.in.dto.PuttingRequest;
//import org.example.in.dto.ReadingHistoryResponse;
//import org.example.in.dto.SuccessResponse;
//import org.example.in.mappers.TrainingMapper;
//import org.example.in.security.Authentication;
//import org.example.model.Indications;
//import org.example.model.User;
//import org.example.service.ReadingService;
//import org.example.service.UserService;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/reading/*")
//public class ReadingServlet extends HttpServlet {
//
//    private UserService userService;
//    private ReadingService readingService;
//    private ObjectMapper jacksonMapper;
//    private TrainingMapper trainingMapper;
//    @Override
//    public void init() throws ServletException {
//        userService = (UserService) getServletContext().getAttribute("userService");
//        jacksonMapper = (ObjectMapper) getServletContext().getAttribute("jacksonMapper");
//        readingService = (ReadingService) getServletContext().getAttribute("readingService");
//        trainingMapper = (TrainingMapper) getServletContext().getAttribute("trainingMapper");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
//
//        if (authentication.isAuth()) {
//            try {
//                if (req.getRequestURI().endsWith("/actual")) {
//                    indicationsHistoryProcess(req, resp, authentication);
//                } else if (req.getRequestURI().endsWith("/all")) {
//                    showHistoryProcess(req, resp, authentication);
//                }
//            } catch (PlayerNotFoundException | ValidationParametersException e) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            } catch (AuthorizeException e) {
//                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            } catch (RuntimeException e) {
//                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            }
//        } else {
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
//        if (authentication.isAuth()) {
//            try(ServletInputStream inputStream = req.getInputStream()) {
//                PuttingRequest request = jacksonMapper.readValue(inputStream, PuttingRequest.class);//получение с Json(десер-я)
//                requestValidation(request); //проверка объекта
//                User user = userService.getByLogin(request.getUserName());//получение полльзователя для проверки
//                if (!authentication.getLogin().equals(user.getName())) throw new AuthorizeException("Incorrect credentials.");
//                String requestURI = req.getRequestURI();//что в юрл запостили
//                if (requestURI.endsWith("/put")) {
//                    readingService.putReading(request.getName(),user.getId(),  request.getValue(), request.getDate());//вызов метода
//                }
//
//                resp.setStatus(HttpServletResponse.SC_OK);
//                jacksonMapper.writeValue(resp.getWriter(), new SuccessResponse("Putting data completed successfully!"));//если удачно
//            } catch (PlayerNotFoundException | ReadingAlreadyExistsException | JsonParseException e) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            } catch (AuthorizeException e) {
//                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            } catch (RuntimeException e) {
//                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(e.getMessage()));
//            }
//        } else {
//            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            jacksonMapper.writeValue(resp.getWriter(), new ExceptionResponse(authentication.getMessage()));
//        }
//    }
//
//    private void requestValidation(PuttingRequest request) throws ValidationParametersException {
//        if (request.getUserName()==null || request.getUserName().isBlank()) {
//            throw new ValidationParametersException("Player login must not be null or empty.");
//        } else if (request.getValue()< 0) {
//            throw new ValidationParametersException("Transaction's amount must not be negative.");
//        }
//    }
//
//    private void indicationsHistoryProcess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws ValidationParametersException, IOException {
//        String login = req.getParameter("login");
//        if (login == null) throw new ValidationParametersException("Login parameter is null!");
//        User user = userService.getByLogin(login);
//        if (!authentication.getLogin().equals(user.getName())) throw new AuthorizeException("Incorrect credentials.");
//        List<Indications> userHistory = readingService.getUserHistory(user.getId());
//        ReadingHistoryResponse response = new ReadingHistoryResponse(user.getName(), trainingMapper.toDTOList(userHistory));
//        resp.setStatus(HttpServletResponse.SC_OK);
//        jacksonMapper.writeValue(resp.getWriter(), response);
//    }
//
//    private void showHistoryProcess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws ValidationParametersException, IOException {
//        String login = req.getParameter("login");
//        if (login == null) throw new ValidationParametersException("Login parameter is null!");
//        User entity = userService.getByLogin(login);
//        if (!authentication.getLogin().equals(entity.getName())) throw new AuthorizeException("Incorrect credentials.");
//
//        List<Indications> userHistory = readingService.getAllHistory(entity.getRole());
//        ReadingHistoryResponse response = new ReadingHistoryResponse(entity.getName(), trainingMapper.toDTOList(userHistory));
//
//        resp.setStatus(HttpServletResponse.SC_OK);
//        jacksonMapper.writeValue(resp.getWriter(), response);
//    }
//
//    public UserService getUserService() {
//        return userService;
//    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    public ReadingService getReadingService() {
//        return readingService;
//    }
//
//    public void setReadingService(ReadingService readingService) {
//        this.readingService = readingService;
//    }
//
//    public ObjectMapper getJacksonMapper() {
//        return jacksonMapper;
//    }
//
//    public void setJacksonMapper(ObjectMapper jacksonMapper) {
//        this.jacksonMapper = jacksonMapper;
//    }
//
//    public TrainingMapper getIndicationsMapper() {
//        return trainingMapper;
//    }
//
//    public void setIndicationsMapper(TrainingMapper trainingMapper) {
//        this.trainingMapper = trainingMapper;
//    }
//}
