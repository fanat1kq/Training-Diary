package org.example.dao.impl;

import org.example.dao.TrainingTypeDAO;
import org.example.utils.ConnectionManager;
import org.example.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TrainingTypeDAOImpl implements TrainingTypeDAO {
    private final ConnectionManager connectionManager;

    public TrainingTypeDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Type> findAll() {
        String sqlFindAll = """
                SELECT * FROM app.training_type
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Type> trainingTypes = new ArrayList<>();
            while (resultSet.next()) {
                trainingTypes.add(buildTypeTraining(resultSet));
            }
            return trainingTypes;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public int getTypeId(String type) {
        String sqlFindById = """
                SELECT * FROM app.training_type WHERE type_name = ?
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

    /**
     * add type of Training
     *
     * @return type type of training
     * @param type type of training
     */
    @Override
    public Type save(Type type) {
        String sqlSave = """
                INSERT INTO app.training_type (type_name) VALUES (?)
                """;

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, type.getTypeName());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Failed to save training type");
            }

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                type.setId(keys.getInt(1));
            } else {
                throw new RuntimeException("Failed to save meter type");
            }
            return type;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save meter type", e);
        }

//        if (trainingType.contains(type)) {
//            throw new AlreadyExistException("Данный тип тренировки уже существует");
//        }
//        trainingType.add(type);
//        return trainingType;
    }

    private Type buildTypeTraining(ResultSet resultSet) throws SQLException {
        return Type.builder()
                .id(resultSet.getInt("id"))
                .typeName(resultSet.getString("type_name"))
                .build();
    }
}
