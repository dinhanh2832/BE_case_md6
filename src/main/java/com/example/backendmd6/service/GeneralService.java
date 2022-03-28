package com.example.backendmd6.service;

import java.util.Optional;

public interface GeneralService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    default void remove(Long id) {
    }
}
