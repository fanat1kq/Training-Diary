package org.example.dao;
import org.example.model.Type;

import java.util.List;
import java.util.Optional;
public interface MainDAO<U, T> {
    T save(T entity);

    List<T> findAll();
}
