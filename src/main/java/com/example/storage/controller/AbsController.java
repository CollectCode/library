package com.example.storage.controller;

import com.example.storage.converter.ConverterImpl;
import com.example.storage.repository.AbsRepository;
import com.example.storage.service.AbsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public abstract class AbsController<
        REQ,
        RES,
        ENTITY,
        ID,
        CVT extends ConverterImpl<ENTITY, REQ, RES>,
        REP extends AbsRepository<ENTITY, ID>,
        SERVICE extends AbsService<REP, REQ, RES, ENTITY, ID, CVT>>
        implements ControllerImpl<RES, REQ> {

    protected final SERVICE service;

    public AbsController(SERVICE service) {
        this.service = service;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<RES> save(REQ request)   {
        RES response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<RES> update(REQ request)  {
        RES response = service.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/remove")
    public ResponseEntity<RES> delete(REQ request) {
        RES response = service.delete(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<RES>> read(REQ request)   {
        List<RES> response = service.read(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
