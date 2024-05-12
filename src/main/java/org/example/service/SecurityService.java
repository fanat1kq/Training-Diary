package org.example.service;

import org.example.in.dto.JwtResponse;
import org.example.model.Users;

public interface SecurityService {
    JwtResponse authorization(Users users);
    Users register(Users users);

}
