package org.example.repository;

import org.example.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeTrainingRepository extends JpaRepository<Type, Integer> {
    int findByTypeName(String type);

}