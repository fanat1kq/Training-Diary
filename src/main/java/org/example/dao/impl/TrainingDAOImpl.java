package org.example.dao.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dao.TrainingDAO;
import org.example.dbconfig.ConnectionManager;
import org.example.exception.NotFoundException;
import org.example.model.Extra;
import org.example.model.Training;
import org.example.model.Type;
import org.example.model.User;
import org.example.model.enumerates.Role;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by fanat1kq on 12/04/2024.
 * implements users, indications
 * using ArraList, Treemap//
 */

@AllArgsConstructor
public class TrainingDAOImpl implements TrainingDAO {
    private final ConnectionManager connectionManager;
    private final Map<Integer, Training> trainings = new HashMap<>();
    public static List<String> trainingType = new ArrayList<>();


    /**
     * get data of training
     *
     * @param user data of user
     * @return HashMap
     */
    @Override
    public List<Map.Entry<Integer, Training>> getTraining(User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            List<Map.Entry<Integer, Training>> list = new ArrayList<>(trainings.entrySet());
            Collections.sort(list, Comparator.comparing(a -> a.getValue().getDate()));
            return list;
        } else for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
            if (entry.getValue().getUserId() == user.getId()) {
                List<Map.Entry<Integer, Training>> list = new ArrayList<>(trainings.entrySet());
                Collections.sort(list, Comparator.comparing(a -> a.getValue().getDate()));
                return list;
            }
        }
        return null;
    }

    /**
     * add data of training in HashMap
     *
     * @param training data of user
     * @return HashMap
     */
    @Override
    public Training addTraining(Training training) {

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
//        int calorie = 0;
//        for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
//            LocalDate date = entry.getValue().getDate();
//            LocalDate after = LocalDate.now().minusMonths(3);
//            if (date.isAfter(after)) {
//                calorie = entry.getValue().getCalorie();
//                calorie++;
//            }
//        }
//        System.out.println("Всего потрачено калорий за последние три месяца " + calorie);
//        return calorie;
        return 0;
    }

    /**
     * add type of Training
     *
     * @return
     */
    @Override
    public Type addType(Type type) {
        String sqlSave = """
                INSERT INTO app.training_type (type_name) VALUES (?)
                """;

        try (Connection connection = ConnectionManager.getConnection();
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

    /**
     * default data of type training
     */
//    @Override
//    public void defaultType() {
//        trainingType.add("кардио");
//        trainingType.add("подтягивания");
//    }

    /**
     * delete training by id
     */
    @Override
    public void deleteTraining(int id) {
        String sqlDelete = """
                DELETE FROM app.training where id=?
                """;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete training", e);
        }
    }

        /**
         * find training by id
         *
         * @param date date of training
         * @param type type of training
         */
        @Override
        public Training findByDate (LocalDate date, String type){
            String sqlFindAllByUserId = """
                SELECT * FROM app.training WHERE date = ? and type=?
                """;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAllByUserId)) {
            preparedStatement.setDate(1, Date.valueOf(date));
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
        for (Map.Entry<Integer, Training> entry : trainings.entrySet()) {
            if (entry.getValue().getId() == newTraining.getId()) {
                trainings.put(newTraining.getId(), newTraining);
                break;
            } else throw new NotFoundException("Такой тренировки нет");
        }
        return trainings.get(newTraining.getId());
    }

    @Override
    public List<Training> findAllByUserId(int userId) {
        String sqlFindByUserId = """
                SELECT * FROM app.training where user_id = ?
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

    @Override
    public Extra addExtra(Extra extra) {
        String sqlSave = """
                INSERT INTO app.training_extra (extra_name, value) VALUES (?,?)
                """;
        try (Connection connection = ConnectionManager.getConnection();
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

