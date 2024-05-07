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
//import org.example.exception.PlayerNotFoundException;
//import org.example.exception.ReadingAlreadyExistsException;
//import org.example.exception.ValidationParametersException;
//import org.example.in.dto.DateDTO;
//import org.example.in.dto.ExceptionResponse;
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
//@WebServlet("/reading/date")
//public class DateServlet extends HttpServlet {
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
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Authentication authentication = (Authentication) getServletContext().getAttribute("authentication");
//        if (authentication.isAuth()) {
//            try(ServletInputStream inputStream = req.getInputStream()) {
//
//                String requestURI = req.getRequestURI();//что в юрл запостили
//                if (requestURI.endsWith("/date")) {
//                    DateDTO request = jacksonMapper.readValue(inputStream, DateDTO.class);//получение с Json(десер-я)
//                    requestValidation(request); //проверка объекта
//                    String user = req.getParameter("login");//получение от юзера
//                    if (user == null) throw new ValidationParametersException("Login parameter is null!");
//                    User entity = userService.getByLogin(user);//получения юзера из бд и проверка по жвт
//                    if (!authentication.getLogin().equals(entity.getName())) throw new AuthorizeException("Incorrect credentials.");
//                    resp.setStatus(HttpServletResponse.SC_OK);
//
//                    List<Indications> indicationsList = readingService.getCounterByMonth(request.year(),  request.month(), entity.getId());//вызов метода
//                    ReadingHistoryResponse response = new ReadingHistoryResponse(entity.getName(), trainingMapper.toDTOList(indicationsList));
//                    jacksonMapper.writeValue(resp.getWriter(), response);
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
//    private void requestValidation(DateDTO request) throws ValidationParametersException {
//        if (request.year()<0 || request.month()<0) {
//            throw new ValidationParametersException("Date must not be negative.");
//        }
//    }
//}
