package org.example.dao;

import org.example.in.AppConsole;
import org.example.models.User;
import org.example.models.enumerates.Role;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;


public class UserDAO {

    static HashMap<User, Role> map=new HashMap<>();
    static User user=new User();
    Scanner scanner = new Scanner(System.in);
    /**
     * login
     * @exception ParseException
     */
    public  void login() throws ParseException {
        while(true) {
            if (map.isEmpty()){
                System.out.println("Сначала создайте пользователя!");
                AppConsole appConsole= new AppConsole();
                appConsole.startApp();
            } else {

                System.out.println("Введите пользователя и пароль:");
                String[] input = scanner.nextLine().split(" ");
                if (input.length<1){
                    System.out.println("невверный формат");
                }
                String nameUser = input[0];
                String password = input[1];
                user.setPassword(password);
                user.setName(nameUser);
                if (map.containsKey(user) ) {
                    System.out.println("Поздравляю! Вы успешно авторизовались!");
                    System.out.println("Права доступа - " + map.get(user));
                    AppConsole appConsole= new AppConsole();
                    appConsole.AppLoop(user,map.get(user));
                } else System.out.println("такого пользователя нет либо неправильный пароль, попробкйте еще раз");
            }
        }}

    /**
     * create user
     * @exception ParseException
     */
    public  void createUser() throws ParseException {
        while(true) {
            System.out.println("Введите нового пользователя, пароль и права доступа(USER, ADMIN) и :");
            System.out.println("Пример: юзер 123 ADMIN");
            System.out.println("ввод производить через пробелы");
            String[] input = scanner.nextLine().split(" ");
            if (input.length<3 ){
                System.out.println("невверный формат");
                continue;
            }
            user.setName(input[0]);
            user.setPassword(input[1]);
            Role role = Role.valueOf(input[2]);
            if (user.getName().length()>0 && user.getPassword().length()>0 && user.getPassword() != null &&
                    !user.getName().equals(null)){
                if (map.containsKey(user)) {
                    System.out.println(user.getName() + " уже существует, создайте другого");
                } else {
                    map.put(user, role);
                    AppConsole appConsole= new AppConsole();
                    appConsole.startApp();
                }
            } else {System.out.println("Некорректное значение! Введите логин и пароль через пробел:");}
        }}
}
