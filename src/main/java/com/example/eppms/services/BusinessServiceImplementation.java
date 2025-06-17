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
        if (id == null) {
            return null;
        }
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null for add operation");
        }
        repository.save(entity);
    }

    @Override
    public void update(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null for update operation");
        }
        repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null for delete operation");
        }
        repository.delete(entity);
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null for delete operation");
        }
        repository.deleteById(id);
    }
}
