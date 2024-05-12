//package org.example.in.servlets;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.in.dto.*;
//import org.example.in.mappers.TrainingMapper;
//import org.example.model.Users;
//import org.example.model.enumerates.Role;
//import org.example.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.example.service.TrainingService;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class TrainingServletTest {
//
//    private TrainingServlet servlet;
//    private HttpServletResponse response;
//    private HttpServletRequest request;
//    private ObjectMapper objectMapper;
//    private TrainingMapper trainingMapper;
//    private TrainingService trainingService;
//    private UserService userService;
//    private ServletContext servletContext;
//    private ServletInputStream servletInputStream;
//
//    @BeforeEach
//    void init() {
//        response = mock(HttpServletResponse.class);
//        request = mock(HttpServletRequest.class);
//        objectMapper = new ObjectMapper();
//        trainingMapper = mock(TrainingMapper.class);
//        trainingService = mock(TrainingService.class);
//        userService = mock(UserService.class);
//        servletContext = mock(ServletContext.class);
//
//        servlet = new TrainingServlet() {
//            @Override
//            public ServletContext getServletContext() {
//                return servletContext;
//            }
//        };
/////как тестировать несколько json в одном классе
//        String AddJson = """
//                {
//                    "time": "test",
//                    "calorie": "1",
//                    "date": "01/01/2024",
//                    "typeId": "1",
//                    "extraId": "1"
//                }""";
//
//        String UpdateJson = """
//                {
//                    "id": "1",
//                    "time": "test",
//                    "calorie": "1",
//                    "date": "01/01/2024",
//                    "typeId": "1",
//                    "extraId": "1"
//                }""";
//        String DeleteJson = """
//                {
//                    "id": "1"
//                }""";
//
//        ByteArrayInputStream addByteArrayInputStream = new ByteArrayInputStream(AddJson.getBytes());
//        ByteArrayInputStream updateByteArrayInputStream = new ByteArrayInputStream(UpdateJson.getBytes());
//        ByteArrayInputStream deleteByteArrayInputStream = new ByteArrayInputStream(DeleteJson.getBytes());
//        servletInputStream = new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return false;
//            }
//
//            @Override
//            public boolean isReady() {
//                return false;
//            }
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//            @Override
//            public int read(){
//                return addByteArrayInputStream.read();
//            }
//        };
//
//        servlet.setTrainingMapper(trainingMapper);
//        servlet.setJacksonMapper(objectMapper);
//        servlet.setTrainingService(trainingService);
//        servlet.setUserService(userService);
//    }
//
//    @Test
//    void testDoGet_success() throws IOException {
//        Users users = new Users("test", "test", Role.ADMIN);
//        users.setId(1);
//        when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
//        when(request.getRequestURI()).thenReturn("/training/history");
//        when(request.getParameter("login")).thenReturn("test");
//        when(userService.getByLogin("test")).thenReturn(users);
//        when(trainingService.getTraining(users.getId(), users.getRole())).thenReturn(Collections.emptyList());
//        when(trainingMapper.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());
//        TrainingHistoryResponse history = new TrainingHistoryResponse("test", Collections.emptyList());
//
//        StringWriter stringWriter = new StringWriter();
//        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
//
//        servlet.doGet(request, response);
//        String json = objectMapper.writeValueAsString(history);
//
//        assertEquals(json, stringWriter.toString());
//    }
////
////    @Test
////    void testDoGet_failed() throws IOException {
////        when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
////
////        when(request.getRequestURI()).thenReturn("/training/history");
////        when(request.getParameter("login")).thenReturn(null);
////        ExceptionResponse exceptionResponse = new ExceptionResponse("Login parameter is null!");
////
////        StringWriter stringWriter = new StringWriter();
////        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
////
////        servlet.doGet(request, response);
////        String json = objectMapper.writeValueAsString(exceptionResponse);
////
////        assertEquals(json, stringWriter.toString());
////    }
//
////    @Test
////    void testDoPost_AddTrainingSuccess() throws IOException {
////        User user = new User("test", "test", Role.ADMIN);
////        Training training = Training.builder().time(1).calorie(1).
////                date(LocalDate.of(2024,1,1)).typeId(1).extraId(1).build();
////        user.setId(1);
////        when(request.getInputStream()).thenReturn(servletInputStream);
////        when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
////        when(request.getRequestURI()).thenReturn("/training/add");
////        when(userService.getByLogin("test")).thenReturn(user);
////
////        when(trainingService.addTraining((AddTrainingRequest) request,user.getId())).thenReturn(training);
////
////        SuccessResponse successResponse = new SuccessResponse("Training add completed successfully!");
////
////        StringWriter stringWriter = new StringWriter();
////        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
////
////        servlet.doPost(request, response);
////        String json = objectMapper.writeValueAsString(successResponse);
////
////        assertEquals(json, stringWriter.toString());
////
////    }
////
////@Test
////void testDoGet_GetStaticsSuccess() throws IOException {
////    User user = new User("test", "test", Role.ADMIN);
////    user.setId(1);
////    when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
////    when(request.getRequestURI()).thenReturn("/training/history");
////    when(request.getParameter("login")).thenReturn("test");
////    when(userService.getByLogin("test")).thenReturn(user);
////    when(trainingService.getStatistic()).thenReturn(0);
////    CaloriesStaticResponse calorie = new CaloriesStaticResponse("test", 0);
////
////    StringWriter stringWriter = new StringWriter();
////    when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
////
////    servlet.doGet(request, response);
////    String json = objectMapper.writeValueAsString(calorie);
////
////    assertEquals(json, stringWriter.toString());
////}
////@Test
////void testDoPost_DeleteTrainingSuccess() throws IOException {
////    User user = new User("test", "test", Role.ADMIN);
////    Training training = Training.builder().time(1).calorie(1).
////            date(LocalDate.of(2024,1,1)).typeId(1).extraId(1).build();
////    user.setId(1);
////    when(request.getInputStream()).thenReturn(servletInputStream);
////    when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
////    when(request.getRequestURI()).thenReturn("/training/add");
////    when(userService.getByLogin("test")).thenReturn(user);
////
////    when(trainingService.addTraining((AddTrainingRequest) request,user.getId())).thenReturn(training);
////
////    SuccessResponse successResponse = new SuccessResponse("Training add completed successfully!");
////
////    StringWriter stringWriter = new StringWriter();
////    when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
////
////    servlet.doPost(request, response);
////    String json = objectMapper.writeValueAsString(successResponse);
////
////    assertEquals(json, stringWriter.toString());
////}
////        @Test
////    void testDoPost_DeleteTrainingSuccess() throws IOException {
////        User user = new User("test", "test", Role.ADMIN);
////        Training training = Training.builder().time(1).calorie(1).
////                date(LocalDate.of(2024,1,1)).typeId(1).extraId(1).build();
////        user.setId(1);
////        when(request.getInputStream()).thenReturn(servletInputStream);
////        when(servletContext.getAttribute("authentication")).thenReturn(new Authentication("test", true, null));
////        when(request.getRequestURI()).thenReturn("/training/add");
////        when(userService.getByLogin("test")).thenReturn(user);
////
////        when(trainingService.addTraining((AddTrainingRequest) request,user.getId())).thenReturn(training);
////
////        SuccessResponse successResponse = new SuccessResponse("Training add completed successfully!");
////
////        StringWriter stringWriter = new StringWriter();
////        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
////
////        servlet.doPost(request, response);
////        String json = objectMapper.writeValueAsString(successResponse);
////
////        assertEquals(json, stringWriter.toString());
////    }
//}