package org.example.service;

import org.example.in.dto.JwtResponse;
import org.example.model.User;

public interface SecurityService {
    JwtResponse authorization(User user);
    User register(User user);

}
