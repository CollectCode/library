package com.example.storage.converter;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.dto.LoanDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanConverter implements ConverterImpl<LoanEntity, LoanCRUDRequest, LoanCRUDResponse> {

    @Override
    public LoanEntity toEntity(LoanCRUDRequest request) {
        LoanDto dto = request.getLoan();
        return LoanEntity.builder()
                .loanDate(dto.getLoanDate())
                .bookId(dto.getBookId())
                .returnedDate(dto.getReturnDate())
                .returnExpireDate(dto.getReturnDate().plusDays(15))
                .userId(dto.getUserId())
                .whetherReturn(dto.getStatus())
                .build();
    }

    @Override
    public LoanCRUDResponse toDto (LoanEntity entity) {
        LoanDto dto = LoanDto.builder()
                .loanDate(entity.getLoanDate())
                .loanId(entity.getLoanId())
                .userId(entity.getUserId())
                .bookId(entity.getBookId())
                .status(entity.getWhetherReturn())
                .returnDate(entity.getReturnedDate())
                .returnExpireDate(entity.getReturnExpireDate())
                .build();
        return LoanCRUDResponse.builder()
                .loan(dto)
                .build();
    }

    @Override
    public List<LoanCRUDResponse> toDtoList(List<LoanEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
