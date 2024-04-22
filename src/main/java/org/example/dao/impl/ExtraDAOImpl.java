package org.example.dao.impl;

import lombok.AllArgsConstructor;
import org.example.dao.ExtraDAO;
import org.example.dbconfig.ConnectionManager;
import org.example.model.Extra;
import org.example.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
@AllArgsConstructor
public class ExtraDAOImpl implements ExtraDAO {
    private final ConnectionManager connectionProvider;
    @Override
    public Extra save(Extra extra) {
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
    @Override
    public List<Extra> findAll() {
        return null;
    }
}
