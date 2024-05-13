package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enumerates.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for authorizations of users
 */
@Entity
@Table(name = "users", schema = "app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @SequenceGenerator(name = "users_generator", sequenceName = "User_id_seq", allocationSize = 1, schema = "app")
    @GeneratedValue(generator = "users_generator", strategy = GenerationType.SEQUENCE)
    public int id;
    @NotNull
    public String login;
    @NotNull
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