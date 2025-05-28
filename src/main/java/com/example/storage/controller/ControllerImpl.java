package com.example.storage.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerImpl<RES, REQ> {
    ResponseEntity<RES> save(REQ request);
    ResponseEntity<RES> update(REQ request);
    ResponseEntity<RES> delete(REQ request);
}
