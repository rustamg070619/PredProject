package dao;


import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User newUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(firstName, lastName) VALUES (?, ?)");
        preparedStatement.setString(1, newUser.getFirstName());
        preparedStatement.setString(2, newUser.getLastName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(User deletedUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE firstName = (?) AND lastName = (?)");
        preparedStatement.setString(1, deletedUser.getFirstName());
        preparedStatement.setString(2, deletedUser.getLastName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void updateUser(User updatedUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstName = (?),lastName = (?) WHERE id = (?)");
        preparedStatement.setString(1, updatedUser.getFirstName());
        preparedStatement.setString(2, updatedUser.getLastName());
        preparedStatement.setLong(3, updatedUser.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<User> getAllUser() throws SQLException {
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM users")) {
            List<User> allUser = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"));
                allUser.add(user);
            }
            return allUser;
        }
    }

    public User getUserById(long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = (?)");
        preparedStatement.setLong(1, id);
        preparedStatement.executeQuery();
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        long idResult = resultSet.getLong(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        preparedStatement.close();
        return new User(idResult, firstName, lastName);
    }
}
