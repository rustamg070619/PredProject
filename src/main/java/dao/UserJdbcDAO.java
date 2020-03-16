package dao;

import model.User;
import util.JDBCConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    Connection connection;

    public UserJdbcDAO() {
        this.connection = JDBCConnection.getSqlConnection();
    }

    @Override
    public void addUser(User added) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users(firstName, lastName) VALUES (?, ?)");
            preparedStatement.setString(1, added.getFirstName());
            preparedStatement.setString(2, added.getLastName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void deleteUser(User deleted) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE firstName = (?) AND lastName = (?)");
            preparedStatement.setString(1, deleted.getFirstName());
            preparedStatement.setString(2, deleted.getLastName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public void updateUser(User updated) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET firstName = (?),lastName = (?) WHERE id = (?)");
            preparedStatement.setString(1, updated.getFirstName());
            preparedStatement.setString(2, updated.getLastName());
            preparedStatement.setLong(3, updated.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public List<User> getAllUser() {
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
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public User getUserById(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = (?)");
            preparedStatement.setLong(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            preparedStatement.close();
            return new User(id, firstName, lastName);
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }
}
