package org.example.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.TrainingTypeDAO;
import org.example.dbconfig.ConnectionManager;
import org.example.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@RequiredArgsConstructor
public class TrainingTypeDAOImpl implements TrainingTypeDAO {
    private final ConnectionManager connectionManager;
    @Override
    public Type save(Type entity) {
        return null;
    }

    @Override
    public int getTypeId(String type) {
        String sqlFindById = """
                SELECT * FROM app.training_type WHERE type = ?
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindById)) {
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }
}
