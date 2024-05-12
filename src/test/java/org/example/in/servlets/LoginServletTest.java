//package org.example.in.servlets;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.model.User;
//import org.example.model.enumerates.Role;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.example.exception.AuthorizeException;
//import org.example.in.dto.ExceptionResponse;
//import org.example.in.dto.JwtResponse;
//import org.example.in.dto.SecurityDTO;
//import org.example.service.SecurityService;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class LoginServletTest {
//
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private SecurityService securityService;
//    private ObjectMapper mapper;
//    private ServletInputStream servletInputStream;
//    private LoginServlet servlet;
//    private SecurityDTO dto;
//    private StringWriter stringWriter;
//    private PrintWriter writer;
//
//    @BeforeEach
//    void beforeEach() {
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//        securityService = mock(SecurityService.class);
//        mapper = new ObjectMapper();
//        String json = """
//                {
//                    "login": "test",
//                    "password": "test"
//                }""";
//
//
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(json.getBytes());
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
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//
//            }
//
//            @Override
//            public int read()  {
//                return byteArrayInputStream.read();
//            }
//        };
//
//        dto = new SecurityDTO("test", "test", Role.ADMIN);
//        stringWriter = new StringWriter();
//        writer = new PrintWriter(stringWriter);
//
//        servlet = new LoginServlet();
//        servlet.setJacksonMapper(mapper);
//        servlet.setSecurityService(securityService);
//    }
//
//    @Test
//    void testDoPostMethod_success() throws IOException{
//        when(request.getInputStream()).thenReturn(servletInputStream);
//
//        when(response.getWriter()).thenReturn(writer);
//
//        JwtResponse jwt = new JwtResponse("test", "accessToken", "refreshToken");
//        when(securityService.authorization(new User(dto.login(), dto.password(),dto.role()))).thenReturn(jwt);
//        String jwtJson = mapper.writeValueAsString(jwt);
//        servlet.doPost(request, response);
//
//        assertEquals(stringWriter.toString(), jwtJson);
//    }
//
//    @Test
//    void testDoPostMethod_failed() throws IOException {
//        when(request.getInputStream()).thenReturn(servletInputStream);
//
//        when(response.getWriter()).thenReturn(writer);
//
//        ExceptionResponse exceptionResponse = new ExceptionResponse("Incorrect password.");
//        when(securityService.authorization(new User(dto.login(), dto.password(),dto.role()))).thenThrow(new AuthorizeException("Incorrect password."));
//        String json = mapper.writeValueAsString(exceptionResponse);
//        servlet.doPost(request, response);
//
//        assertEquals(stringWriter.toString(), json);
//    }
//}