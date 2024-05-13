package org.example.in.dto;

import org.example.model.enumerates.Role;

import javax.validation.constraints.NotNull;

/**
 * Request data for security
 *
 * @param login the player login
 * @param password the player password
 */
//проверка на нал при входе
public record SecurityRequestAuth(@NotNull(message = "Login must be not null.") String login,
                                  @NotNull(message = "Password must be not null.") String password
                                 ) {
}
