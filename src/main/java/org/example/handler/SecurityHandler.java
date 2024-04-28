package org.example.handler;
import org.example.in.AppConsole;
import org.example.controller.MainController;
import org.example.model.User;
import org.example.model.enumerates.Role;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Handler class for security-related operations. Provides methods for user registration,
 * user authorization, and related security functionalities.
 */
public class SecurityHandler {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Handles the user registration process, prompting for login and password,
     * registering the user, and loading the authorized user into the application context.
     */
    public static void handleAuthorization(MainController mainController) throws ParseException {
        System.out.println("Введите логин и пароль:");
        String[] input1 = scanner.nextLine().split(" ");
        if (input1.length < 1) {
            System.out.println("невверный формат");
//            continue;
        }
        String name = input1[0];
        String password = input1[1];
        User user = new User(name, password);
        User login = mainController.login(user);
        AppConsole.AppLoop(login);
    }

    /**
     * Handles the user authorization process, prompting for login and password,
     * authorizing the user, and loading the authorized user into the application context.
     */
    public static void handleRegistration(MainController mainController) {
        final String userExample = """
                            Введите нового пользователя: логин, пароль и права доступа(user, admin)
                            Пример: login 123 admin
                            ввод производить через пробелы
                            """;
        System.out.println(userExample);
        String[] input2 = scanner.nextLine().split(" ");
        if (input2.length < 3) {
            System.out.println("неверный формат");
//            continue;
        }
        String name1 = input2[0];
        String password1 = input2[1];
        Role role = Role.valueOf(input2[2].toUpperCase());
        User user2 = new User(name1, password1, role);
        mainController.createUser(user2);
    }
}