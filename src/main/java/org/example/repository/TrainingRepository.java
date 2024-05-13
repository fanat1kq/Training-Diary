package org.example.repository;

import org.example.model.Training;
import org.example.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * The transaction repository
 */
public interface TrainingRepository extends JpaRepository<Training, Integer> {

    /**
     * Find all transactions by player id
     *

     * @return the player's list of transactions
     */
    List<Training> findAllByUserId(int userId);
    Training findAllByDateAndId(LocalDate date, int typeId);

    void deleteById(int id);

//    Training updateById(Users users, Training training);
}

