package org.example.service;

import org.example.model.Users;

public interface UserService {
    Users getByLogin(String login);

}
