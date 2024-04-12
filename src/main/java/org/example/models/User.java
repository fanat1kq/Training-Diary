package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.enumerates.Role;

/**
 * Created by fanat1kq on 12/04/2024.
 * This class is responsible for autorizations of users
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public String name;
    public String password;
    public Role role;

}
