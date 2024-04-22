package org.example.dao;

import org.example.model.User;

public interface SecurityDAO {
    User findByLogin(String name);
    User createUser(User user);


}
