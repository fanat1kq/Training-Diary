package org.example.in.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.dao.ExtraDAO;
import org.example.dao.TrainingDAO;
import org.example.dao.TrainingTypeDAO;
import org.example.dao.UserDAO;
import org.example.dao.impl.ExtraDAOImpl;
import org.example.dao.impl.TrainingDAOImpl;
import org.example.dao.impl.TrainingTypeDAOImpl;
import org.example.dao.impl.UserDAOImpl;
import org.example.service.ExtraService;
import org.example.service.SecurityService;
import org.example.service.TrainingService;
import org.example.service.TypeService;
import org.example.service.UserService;
import org.example.service.impl.ExtraServiceImpl;
import org.example.service.impl.SecurityServiceImpl;
import org.example.service.impl.TrainingServiceImpl;
import org.example.service.impl.TypeServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.example.util.ConnectionManager;
import org.example.in.mappers.TrainingMapper;
import org.example.in.mappers.UserMapper;
import org.example.in.security.JwtTokenProvider;
import org.example.liquibase.Liquibase;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@WebListener
    public class ApplicationContextListener implements ServletContextListener {

    private Properties properties;
    private ConnectionManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {//1 для запуска
        final ServletContext servletContext = sce.getServletContext();

        loadProperties(servletContext);
        databaseConfiguration(servletContext);
        serviceContextInit(servletContext);

        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.findAndRegisterModules(); //для работы с датой в json
        servletContext.setAttribute("jacksonMapper", jacksonMapper);
        servletContext.setAttribute("userMapper", UserMapper.INSTANCE);
        servletContext.setAttribute("trainingMapper", TrainingMapper.INSTANCE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private void loadProperties(ServletContext servletContext) {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(servletContext.getResourceAsStream("/WEB-INF/classes/application.properties"));
                servletContext.setAttribute("servletProperties", properties);
            }catch (FileNotFoundException e ) {
                throw new RuntimeException("Property file not found!");
            } catch (IOException e) {
                throw new RuntimeException("Error reading configuration file: " + e.getMessage());
            }
        }
    }
    private void databaseConfiguration(ServletContext servletContext) {
        String dbUrl = properties.getProperty("db.url");
        String dbUser = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String dbDriver = properties.getProperty("db.driver");
        connectionManager = new ConnectionManager(dbUrl, dbUser, dbPassword,dbDriver);
        servletContext.setAttribute("connectionProvider", connectionManager);
        Liquibase migrationService=new Liquibase(connectionManager);
        migrationService.start();
        servletContext.setAttribute("migrationService", migrationService);
    }

    private void serviceContextInit(ServletContext servletContext) {
        UserDAO userDAO = new UserDAOImpl(connectionManager);
        TrainingDAO trainingDAO = new TrainingDAOImpl(connectionManager);
        TrainingTypeDAO trainingTypeDAO = new TrainingTypeDAOImpl(connectionManager);
        ExtraDAO extraDAO = new ExtraDAOImpl(connectionManager);

        UserService userService = new UserServiceImpl(userDAO);
        JwtTokenProvider tokenProvider = new JwtTokenProvider(
                properties.getProperty("jwt.secret"),
                Long.parseLong(properties.getProperty("jwt.access")),//60 минут
                Long.parseLong(properties.getProperty("jwt.refresh")),//180 дней
                userService
        );
        SecurityService securityService = new SecurityServiceImpl(userDAO, tokenProvider);
        TrainingService trainingService = new TrainingServiceImpl(trainingDAO);
        TypeService typeService = new TypeServiceImpl(trainingTypeDAO);
        ExtraService extraService = new ExtraServiceImpl(extraDAO);

        servletContext.setAttribute("tokenProvider", tokenProvider);
        servletContext.setAttribute("securityService", securityService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("readingService", trainingService);
        servletContext.setAttribute("typeService", typeService);
        servletContext.setAttribute("extraService", extraService);
    }
}
