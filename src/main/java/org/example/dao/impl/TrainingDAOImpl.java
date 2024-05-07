package org.example.dao.impl;

import lombok.AllArgsConstructor;

import org.example.dao.TrainingDAO;
import org.example.dbconfig.ConnectionManager;

import org.example.model.Training;
import org.example.model.User;


import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by fanat1kq on 12/04/2024.
 * implements users, indications
 * using ArrayList, Treemap//
 */


public class TrainingDAOImpl implements TrainingDAO {
    private final ConnectionManager connectionManager;

    public TrainingDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * add data of training in HashMap
     *
     * @param training data of user
     * @return HashMap
     */
    @Override
    public Training save(Training training) {
        String sqlSave = """
                INSERT INTO app.training (time, calorie, date, user_id, type_id, extra_id)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, training.getTime());
            preparedStatement.setInt(2, training.getCalorie());
            preparedStatement.setDate(3, Date.valueOf(training.getDate()));
            preparedStatement.setInt(4, training.getUserId());
            preparedStatement.setInt(5, training.getTypeId());
            preparedStatement.setInt(6, training.getExtraId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getInt(1);
                if (id != 0) {
                    training.setId((int) id);
                } else {
                    throw new RuntimeException("Failed to generate ID for training");
                }
            }
            return training;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save training", e);
        }
    }


    /**
     * get static of using calories meantime training
     *
     * @return int
     */
    @Override
    public int getStatistic() {
        String sqlStatic = """
                	select SUM(calorie) from app.training WHERE date >= CURRENT_DATE - INTERVAL '3 months'
        """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlStatic)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            int result= 0;
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            return result;
    } catch (SQLException e) {
            throw new RuntimeException("Failed to get static");
        }
    }
    /**
     * delete training by id
     */
    @Override
    public void deleteTraining(int id) {
        String sqlDelete = """
                DELETE FROM app.training where id=?
                """;

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete training", e);
        }
    }

        /**
         * find training by id
         * @param date date of training
         * @param typeId type id of training
         */
        @Override
        public Training findByDate (LocalDate date, int typeId){
            String sqlFindAllByUserId = """
                SELECT * FROM app.training WHERE date = ? and type_id=?
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAllByUserId)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setInt(2, typeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Training result = null;
            while (resultSet.next()) {
                result = buildTraining(resultSet);
            }
            return result;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Training updateTraining(User newUser, Training newTraining) {
        String sqlUpdate = """
                UPDATE app.training SET time = ?, calorie = ?, date = ?, user_id = ?, type_id = ?, extra_id = ?
                 WHERE id = ?
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, newTraining.getTime());
            preparedStatement.setInt(2, newTraining.getCalorie());
            preparedStatement.setDate(3, Date.valueOf(newTraining.getDate()));
            preparedStatement.setInt(4, newTraining.getUserId());
            preparedStatement.setInt(5, newTraining.getTypeId());
            preparedStatement.setInt(6, newTraining.getExtraId());
            preparedStatement.setInt(7, newTraining.getId());
            preparedStatement.executeUpdate();
            return newTraining;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update training", e);
        }
    }

    @Override
    public List<Training> findAllByUserId(int userId) {
        String sqlFindByUserId = """
                SELECT * FROM app.training where user_id = ?
                ORDER BY date
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByUserId)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Training> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(buildTraining(resultSet));
            }
            return result;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }
    @Override
    public List<Training> findAll() {
        String sqlFindAll = """
                SELECT * FROM app.training
                 ORDER BY date
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Training> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(buildTraining(resultSet));
            }
            return result;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }



    private Training buildTraining(ResultSet resultSet) throws SQLException {
        return Training.builder()
                .id(resultSet.getInt("id"))
                .time(resultSet.getInt("time"))
                .calorie(resultSet.getInt("calorie"))
                .date(resultSet.getDate("date").toLocalDate())
                .userId(resultSet.getInt("user_id"))
                .typeId(resultSet.getInt("type_id"))
                .extraId(resultSet.getInt("extra_id"))
                .build();
    }

}

