package com.example.storage.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerImpl<RES, REQ, ID> {
    ResponseEntity<RES> save(REQ request);
    ResponseEntity<RES> update(REQ request);
    ResponseEntity<RES> delete(ID id);
}
