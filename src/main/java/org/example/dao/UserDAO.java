package org.example.dao;

import org.example.model.User;

public interface UserDAO extends MainDAO<User>{
    User findByLogin(String name);


}
