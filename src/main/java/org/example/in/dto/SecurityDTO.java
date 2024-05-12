package org.example.in.dto;

import org.example.model.enumerates.Role;

public record SecurityDTO(String login, String password, Role role) {

}
