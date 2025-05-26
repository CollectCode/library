package com.example.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface AbsRepository<ENTITY, ID extends Serializable> extends JpaRepository<ENTITY, ID> {
	
}
