package com.example.eppms.services;

import java.util.List;

public interface BusinessServices<T, Integer>{
    List<T> getAll();
    T getById(Integer id);
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteById(Integer id);
}
