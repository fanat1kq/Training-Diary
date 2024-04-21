package org.example.dao;
import java.util.List;
import java.util.Optional;
public interface MainDAO<U, T> {
    T save(T entity);

}
