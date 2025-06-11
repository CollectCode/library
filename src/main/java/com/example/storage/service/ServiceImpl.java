package com.example.storage.service;

import java.util.List;

public interface ServiceImpl<REQ, RES, ID> {
    RES save(REQ request);
    RES update(REQ request, ID id);
    RES delete(ID request);
    List<RES> read(REQ request);
}
