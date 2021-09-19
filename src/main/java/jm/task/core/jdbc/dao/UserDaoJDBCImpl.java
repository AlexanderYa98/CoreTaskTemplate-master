package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.connection();
        try (Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(40) NOT NULL, " +
                    " lastName VARCHAR (40) NOT NULL, " +
                    " age INT not NULL, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.connection();
        try (Statement statement = connection.createStatement()) {
            String query = "DROP TABLE if EXISTS users";
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connect = Util.connection();
        try (PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("User с именем – " + name+" "+ lastName + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        Connection connection = Util.connection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            statement.setLong(1, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Connection connection = Util.connection();
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong(1));
                user.setName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setAge(result.getByte(4));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection connection = Util.connection();
        try (Statement statement = connection.createStatement()) {
            String query = "DELETE FROM users";
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
