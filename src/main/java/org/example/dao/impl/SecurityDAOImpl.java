package org.example.dao.impl;

import org.example.dao.SecurityDAO;
import org.example.model.enumerates.Role;
import org.example.model.User;

import java.util.*;

public class SecurityDAOImpl implements SecurityDAO {
    private final Map<Integer, User> users = new HashMap<>();
    private static int ID = 1;

    /**
     * login
     *
     * @return User
     * @param name name of user
     */
    @Override
    public User findByLogin(String name) {
        User user =null;
        List<User> list = new ArrayList<>(users.values());
        for ( User s : list) {
            if (s.getLogin().equals(name)) {
                user = s;
                break;
            }
        }
       return user;
    }

    /**
     * create user
     * @return User
     */
    @Override
    public  User createUser(User user) {
        ID++;
        user.setId(ID);
        users.put(user.getId(), user);
        return users.get(user.getId());
        }
    /**
     * add default user
     * @return User
     */
    @Override
    public void defaultUser() {
        users.put(ID, new User("admin","admin", Role.ADMIN));
    }
}

