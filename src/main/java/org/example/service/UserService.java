package org.example.service;

import org.example.model.User;

public interface UserService {
    User getByLogin(String login);

}
