package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexander","Yakovlev", (byte) 21);
        userService.saveUser("Vasiliy","Ivanov", (byte) 30);
        userService.saveUser("Ivan","Petrov", (byte) 24);
        userService.saveUser("Petr","Ivanov", (byte) 28);
        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
