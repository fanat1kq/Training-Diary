package org.example.repository;

import org.example.model.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The transaction repository
 */
public interface ExtraInformationRepository extends JpaRepository<Extra, Integer> {

}

