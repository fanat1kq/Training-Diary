package org.example.dao;

import java.util.List;

public interface MainDAO<T> {
    T save(T entity);

    List<T> findAll();
}
