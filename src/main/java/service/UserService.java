package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserDAO userDAO = new UserHibernateDAO();

    public boolean addUser(User newUser) {
        userDAO.addUser(newUser);
        return true;
    }

    public boolean deleteUser(User deletedUser) {
        userDAO.deleteUser(deletedUser);
        return true;
    }

    public boolean updateUser(User updatedUser) {
        userDAO.updateUser(updatedUser);
        return true;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> result = null;
        result = userDAO.getAllUser();
        return result;
    }

    public User getUserById(long id) throws SQLException {
        return userDAO.getUserById(id);
    }

}
