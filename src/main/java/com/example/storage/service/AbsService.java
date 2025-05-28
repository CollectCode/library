package com.example.storage.service;

import com.example.storage.converter.ConverterImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

public abstract class AbsService<
        REP extends JpaRepository<ENTITY, ID>,
        REQ,
        RES,
        ENTITY,
        ID extends Serializable,
        CVT extends ConverterImpl<ENTITY, REQ, RES>>
        implements ServiceImpl<REQ, RES>  {

    protected final REP repository;
    protected final CVT converter;

    public AbsService(REP repository, CVT converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public RES save(REQ request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        ENTITY entity = converter.toEntity(request);
        ENTITY saved = repository.save(entity);
        return converter.toDto(saved);
    }

    @Override
    public RES update(REQ request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        ENTITY entity = converter.toEntity(request);
        ENTITY updated = repository.save(entity);
        return converter.toDto(updated);
    }

    @Override
    public RES delete(REQ request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        ENTITY entity = converter.toEntity(request);
        repository.delete(entity);
        return converter.toDto(entity);
    }

    @Override
    public List<RES> read(REQ request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        List<ENTITY> entities = repository.findAll();
        return converter.toDtoList(entities);
    }
}
