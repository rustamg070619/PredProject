package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHibernateDAO() {
        this.sessionFactory = new DBHelper().getSessionFactory();
    }

    @Override
    public void addUser(User added) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(added);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(User deleted) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(deleted);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User updated) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(updated);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User WHERE id = :id ")
                                .setParameter("id", id)
                                .list();
        session.close();
        return list.get(0);
    }
}
