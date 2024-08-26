package br.com.lux.dao;

import br.com.lux.config.ConnectionPoolConfig;
import br.com.lux.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean verifyCredentials(User user) {
        String SQL = "SELECT * FROM USERS WHERE EMAIL = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, user.getEmail());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String password = resultSet.getString("password");
                    if (password.equals(user.getPassword())) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

}
