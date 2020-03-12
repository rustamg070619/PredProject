package service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public UserService() {
    }

    public boolean addUser(User newUser) {
        UserDAO dao = getUserDAO();
        try {
            dao.addUser(newUser);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(User deletedUser) {
        UserDAO dao = getUserDAO();
        try {
            dao.deleteUser(deletedUser);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User updatedUser) {
        UserDAO dao = getUserDAO();
        try {
            dao.updateUser(updatedUser);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUser() throws SQLException {
        UserDAO dao = getUserDAO();
        List<User> result = null;
        result = dao.getAllUser();
        return result;
    }

    public User getUserById(long id) throws SQLException {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=admin12345").
                    append("&useSSL=false&serverTimezone=UTC");
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }
}
