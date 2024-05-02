package org.example.dao;

import org.example.model.User;

public interface SecurityDAO extends MainDAO<User> {
    User findByLogin(String name);


}
