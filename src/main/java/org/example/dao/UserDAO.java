package org.example.dao;

import org.example.exception.AuthorizeException;
import org.example.exception.RegisterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.in.AppConsole;
import org.example.models.User;
import org.example.models.enumerates.Role;
import java.text.ParseException;
import java.util.HashMap;

public class UserDAO {
    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
    static HashMap<User, Role> map=new HashMap<>();
    static User user=new User();
    /**
     * login
     *
     * @return boolean
     * @throws ParseException
     * @param name name of user
     * @param password password of user
     */
    public boolean login(String name, String password) throws ParseException {
        log.info("The player trying to log in with login " + name + " and password " + password);
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
                } else throw new AuthorizeException("Такого пользователя нет либо неправильный пароль, попробуйте еще раз");
            }
            return true;
        }
    /**
     * create user
     * @exception ParseException
     * @param name name of user
     * @param password password of user
     * @param role1 role of user
     */
    public  void createUser(String name, String password, String role1) throws ParseException {

        log.info("The player trying to register with login " + name + " and password " + password);
            user.setName(name);
            user.setPassword(password);
            Role role = Role.valueOf(role1);
            if (user.getName().length()>0 && user.getPassword().length()>0 && user.getPassword() != null &&
                    !user.getName().equals(null)){
                if (map.containsKey(user)) {
                    throw new RegisterException("Такой пользователь уже существует, попробуйте другого");
                } else {
                    map.put(user, role);
                    AppConsole appConsole= new AppConsole();
                    appConsole.startApp();
                }
            } else throw new RegisterException("Некорректное значение! Введите логин и пароль через пробел:");
        }
}

