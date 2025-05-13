package com.example.eppms.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class BusinessServiceImplementation<T, Integer> implements BusinessServices<T, Integer> {

    protected JpaRepository<T, Integer> repository;
    public BusinessServiceImplementation(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T getById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void add(T entity) {
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
