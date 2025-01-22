package vladdossik.jdbc.jpa.service;

import vladdossik.jdbc.jpa.dao.UserDao;
import vladdossik.jdbc.jpa.dao.UserDaoHibernateImpl;
import vladdossik.jdbc.jpa.dao.UserDaoJDBCImpl;
import vladdossik.jdbc.jpa.model.User;
import vladdossik.jdbc.jpa.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() throws SQLException {
        this.userDao = new UserDaoHibernateImpl();
    }

    @Override
    public void createUsersTable() throws RuntimeException, SQLException {
        userDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() throws RuntimeException, SQLException {
        userDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws RuntimeException, SQLException {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) throws RuntimeException, SQLException {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws RuntimeException, SQLException {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() throws RuntimeException, SQLException {
        userDao.cleanUsersTable();
    }
}