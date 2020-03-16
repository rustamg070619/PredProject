package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
   public void addUser(User added);
   public void deleteUser(User deleted);
   public void updateUser(User updated);
   public List<User> getAllUser();
   public User getUserById(long id);
}
