package vladdossik.jdbc.jpa;


import vladdossik.jdbc.jpa.model.User;
import vladdossik.jdbc.jpa.service.UserService;
import vladdossik.jdbc.jpa.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        try {
            userService.createUsersTable();
            System.out.println("Users table created");
            String[] names = {"Bob", "Alice", "Tom", "Eva"};
            String[] lastNames = {"Smith", "Jones", "Brown", "White"};
            byte[] age = {20, 25, 30, 35};

            for (int i = 0; i < names.length; i++) {
                userService.saveUser(names[i], lastNames[i], age[i]);
                System.out.println("User " + names[i] + " was added");
            }

            List<User> users = userService.getAllUsers();
            users.forEach(System.out::println);
            userService.cleanUsersTable();
            System.out.println("Table was cleaned");
            userService.dropUsersTable();
            System.out.println("Table was dropped");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
