package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerates.Role;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for authorizations of users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    public int id;
    public String login;
    public String password;
    public Role role;

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Users(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}