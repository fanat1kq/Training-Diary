package org.example.dao.impl;

import org.example.dao.SecurityDAO;
import org.example.dbconfig.ConnectionManager;
import org.example.model.enumerates.Role;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SecurityDAOImpl implements SecurityDAO {
    private final Map<Integer, User> users = new HashMap<>();
    private static int ID = 1;

    /**
     * login
     *
     * @return User
     * @param name name of user
     */
    @Override
    public User findByLogin(String name) {
        User user =null;
        List<User> list = new ArrayList<>(users.values());
        for ( User s : list) {
            if (s.getLogin().equals(name)) {
                user = s;
                break;
            }
        }
       return user;
    }

    /**
     * create user
     * @return User
     */
    @Override
    public  User createUser(User user) {
        String sql = "INSERT INTO app.users (login, password,role) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.login);
            statement.setString(2, user.password);
            statement.setString(3, String.valueOf(user.role));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при регистрации игрока: " + e.getMessage());
        }
        return user;
//        ID++;
//        user.setId(ID);
//        users.put(user.getId(), user);
//        return users.get(user.getId());
        }
    /**
     * add default user
     * @return User
     */
    @Override
    public void defaultUser() {
        users.put(ID, new User("admin","admin", Role.ADMIN));
    }
}

