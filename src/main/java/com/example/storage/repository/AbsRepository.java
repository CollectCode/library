package com.example.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsRepository<ENTITY, ID> extends JpaRepository<ENTITY, ID> {
    List<ENTITY> findAll(ENTITY entity);
}
