package org.example.in.dto;

import org.example.model.enumerates.Role;

//для входа под пользователем(на сервлет с json)
public record SecurityDTO(String login, String password, Role role) {

}
