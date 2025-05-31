package com.example.storage.service;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.dto.LoanDto;
import com.example.storage.enums.Return;
import com.example.storage.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService extends AbsService<
        LoanRepository,
        LoanCRUDRequest,
        LoanCRUDResponse,
        LoanEntity,
        Long,
        LoanConverter>{

    public LoanService(LoanRepository repository, LoanConverter converter) {
        super(repository, converter);
    }

    public List<LoanCRUDResponse> searchByBookId(Long bookId) {
        List<LoanEntity> entities = repository.findAllByBookId(bookId);
        return converter.toDtoList(entities);
    }

    public List<LoanCRUDResponse> searchByUserId(Long userId) {
        List<LoanEntity> entities = repository.findAllByUserId(userId);
        return converter.toDtoList(entities);
    }

    @Override
    public LoanCRUDResponse delete(LoanCRUDRequest request) {
        LoanDto dto = request.getLoan();
        LoanEntity entity = repository.findById(dto.getLoanId()).orElseThrow(() -> new RuntimeException("Loan not found"));
        Return status = Return.RETURNED;
        if(entity.getReturnExpireDate().isAfter(LocalDate.now())) {
            status = Return.RETURN_LATE;
        }
        entity.updateReturnType(status);
        LoanEntity savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }
}
