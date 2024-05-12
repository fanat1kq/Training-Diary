package org.example.repository;

import org.example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The player repository
 */
public interface UserRepository extends JpaRepository<Users, Integer> {
    /**
     * Find player in database by login
     *
     * @param login the login
     * @return optional of player. If not fount optional is empty
     */
    Users findByLogin(String login);
}
