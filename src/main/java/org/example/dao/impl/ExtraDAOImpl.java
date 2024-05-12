package org.example.dao.impl;

import org.example.dao.ExtraDAO;
import org.example.util.ConnectionManager;
import org.example.model.Extra;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtraDAOImpl implements ExtraDAO {
    private final ConnectionManager connectionManager;

    public ExtraDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Extra save(Extra extra) {
        String sqlSave = """
                INSERT INTO app.training_extra (extra_name, value) VALUES (?,?)
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, extra.getName());
            preparedStatement.setInt(2, extra.getValue());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Failed to save training extra information");
            }

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                extra.setId(keys.getInt(1));
            } else {
                throw new RuntimeException("Failed to save training extra information");
            }

            return extra;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save extra information", e);
        }
    }
    @Override
    public List<Extra> findAll() {
        String sqlFindAll = """
                SELECT * FROM app.training_extra
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Extra> trainingExtra = new ArrayList<>();
            while (resultSet.next()) {
                trainingExtra.add(buildExtraTraining(resultSet));
            }
            return trainingExtra;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
    private Extra buildExtraTraining(ResultSet resultSet) throws SQLException {
        return Extra.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("extra_name"))
                .value(resultSet.getInt("value"))
                .build();
    }
}
