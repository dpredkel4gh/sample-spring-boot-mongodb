package com.example.sample.service;

import java.util.Collection;

public interface Service<T, D> {

    Collection<T> findAll();

    T findByUuid(String uuid);

    T save(D dto);

    void deleteByUuid(String uuid);

    void update(T dto);
}
