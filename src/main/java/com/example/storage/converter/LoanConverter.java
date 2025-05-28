package com.example.storage.converter;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanConverter implements ConverterImpl<LoanEntity, LoanCRUDRequest, LoanCRUDResponse> {

    @Override
    public LoanEntity toEntity(LoanCRUDRequest request) {
        return null;
    }

    @Override
    public LoanCRUDResponse toDto (LoanEntity entity) {
        return null;
    }

    @Override
    public List<LoanCRUDResponse> toDtoList(List<LoanEntity> entities) {
        return List.of();
    }
}
