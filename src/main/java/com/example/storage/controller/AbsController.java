package com.example.storage.controller;

import com.example.storage.converter.ConverterImpl;
import com.example.storage.repository.AbsRepository;
import com.example.storage.service.AbsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Slf4j
public abstract class AbsController<
        REQ,
        RES,
        ENTITY,
        ID extends Serializable,
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
    public ResponseEntity<RES> save(@RequestBody REQ request)   {
        RES response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<RES> update(@RequestBody REQ request)  {
        RES response = service.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/remove")
    public ResponseEntity<RES> delete(@RequestBody REQ request) {
        RES response = service.delete(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<RES>> read(@RequestBody REQ request)   {
        List<RES> response = service.read(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
