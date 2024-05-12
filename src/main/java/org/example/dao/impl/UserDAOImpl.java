package org.example.dao.impl;

import org.example.dao.UserDAO;
import org.example.util.ConnectionManager;
import org.example.model.User;
import org.example.model.enumerates.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final ConnectionManager connectionManager;

    public UserDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * login
     *
     * @return User
     * @param login name of user
     */
    @Override
    public User findByLogin(String login) {
        String sqlFindByLogin = """
                SELECT * FROM app.users WHERE login = ?
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByLogin)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next() ?
                    buildUser(resultSet)
                    : null;
        } catch (SQLException e) {
            return null;
        }
    }


    /**
     * create user
     * @return User
     */
    @Override
    public  User save(User user) {
        String sql = "INSERT INTO app.users (login, password,role) VALUES (?, ?, ?)";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.login);
            statement.setString(2, user.password);
            statement.setString(3, String.valueOf(user.role));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при регистрации игрока: " + e.getMessage());
        }
        return user;
    }

    /////////////
    @Override
    public List<User> findAll() {
        return null;
    }
    /**
     * add default user
     * @return User
     */
    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .login(resultSet.getString("login"))
                .role(Role.valueOf(resultSet.getString("role")))
                .password(resultSet.getString("password"))
                .build();
    }
}

