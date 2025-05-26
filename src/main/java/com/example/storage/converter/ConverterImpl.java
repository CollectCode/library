package com.example.storage.converter;

import java.util.List;

public interface ConverterImpl<ENTITY, REQ, RES> {
    ENTITY toEntity(REQ dto);
    RES toDto(ENTITY entity);
    List<RES> toDtoList(List<ENTITY> entities);
}
