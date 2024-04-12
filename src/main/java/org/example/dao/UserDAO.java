package org.example.dao;

import org.example.in.AppConsole;
import org.example.models.User;
import org.example.models.enumerates.Role;
import java.text.ParseException;
import java.util.HashMap;

public class UserDAO {

    static HashMap<User, Role> map=new HashMap<>();
    static User user=new User();
    /**
     * login
     *
     * @return
     * @throws ParseException
     */
    public boolean login(String name, String password) throws ParseException {

            if (map.isEmpty()){
                System.out.println("Сначала создайте пользователя!");
                AppConsole appConsole= new AppConsole();
                appConsole.startApp();
            } else {
                user.setPassword(password);
                user.setName(name);
                if (map.containsKey(user) ) {
                    System.out.println("Поздравляю! Вы успешно авторизовались!");
                    System.out.println("Права доступа - " + map.get(user));
                    AppConsole appConsole= new AppConsole();
                    appConsole.AppLoop(user,map.get(user));
                } else System.out.println("такого пользователя нет либо неправильный пароль, попробуйте еще раз");
            }
            return true;
        }
    /**
     * create user
     * @exception ParseException
     */
    public  void createUser(String name, String password, String role1) throws ParseException {
            user.setName(name);
            user.setPassword(password);
            Role role = Role.valueOf(role1);
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
        }
}

