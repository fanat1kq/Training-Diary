package org.example.model;

import lombok.*;
import org.example.model.enumerates.Role;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for autorizations of users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public int id;
    public String login;
    public String password;
    public Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
