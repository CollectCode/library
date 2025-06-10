package com.example.storage.converter;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.dto.LoanDto;
import com.example.storage.enums.Return;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanConverter implements ConverterImpl<LoanEntity, LoanCRUDRequest, LoanCRUDResponse> {

    @Override
    public LoanEntity toEntity(LoanCRUDRequest request) {
        return LoanEntity.builder()
                .loanDate(request.getLoanDate())
                .bookId(request.getBookId())
                .returnedDate(request.getReturnDate())
                .returnExpireDate(request.getLoanDate().plusDays(15))
                .userId(request.getUserId())
                .whetherReturn(Return.LOANING)
                .build();
    }

    @Override
    public LoanCRUDResponse toDto (LoanEntity entity) {
        LoanCRUDResponse dto;
        if(entity.getReturnedDate() == null) {
            return LoanCRUDResponse.builder()
                    .loanDate(entity.getLoanDate())
                    .loanId(entity.getLoanId())
                    .userId(entity.getUserId())
                    .bookId(entity.getBookId())
                    .returnExpireDate(entity.getReturnExpireDate())
                    .status(entity.getWhetherReturn())
                    .build();
        } else {
            return LoanCRUDResponse.builder()
                    .loanDate(entity.getLoanDate())
                    .loanId(entity.getLoanId())
                    .userId(entity.getUserId())
                    .bookId(entity.getBookId())
                    .returnDate(entity.getReturnedDate())
                    .returnExpireDate(entity.getReturnExpireDate())
                    .status(entity.getWhetherReturn())
                    .build();
        }
    }

    @Override
    public List<LoanCRUDResponse> toDtoList(List<LoanEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
