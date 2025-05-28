package com.example.storage.service;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.repository.LoanRepository;
import org.springframework.stereotype.Service;

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
}
