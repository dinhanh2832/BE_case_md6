package com.example.backendmd6.service;

import com.example.backendmd6.model.Work;

public interface WorkService {

    Iterable<Work> findAllByName(String name);

    Iterable<Work> findAll();
}
