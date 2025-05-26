package com.example.storage.service;

import java.util.List;

public interface ServiceImpl<REQ, RES> {
    RES save(REQ request);
    RES update(REQ request);
    RES delete(REQ request);
    List<RES> read(REQ request);
}
