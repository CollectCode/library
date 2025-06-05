package com.example.storage.service;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.dto.LoanReturnRequest;
import com.example.storage.enums.Return;
import com.example.storage.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Slf4j
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

    public LoanCRUDResponse loanBook(LoanCRUDRequest request) throws InstanceAlreadyExistsException {
        LoanEntity entity = converter.toEntity(request);
        if(repository.existsByBookIdAndWhetherReturn(entity.getBookId(), Return.LOANING))   {
            throw new InstanceAlreadyExistsException("이미 대출된 책은 반납 후 대출하시기 바랍니다.");
        }
        LoanEntity savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }

    public LoanCRUDResponse returnBook(LoanReturnRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("요청은 null값이 올 수 없습니다!");
        }

        LoanEntity entity = repository.findByBookIdAndWhetherReturn(request.getBookId(), Return.LOANING);

        if(entity == null) {
            throw new IllegalArgumentException("현재 대출중인 책이 아닙니다.");
        }

        Return status = Return.RETURNED;
        if(request.getReturnDate().isAfter(entity.getReturnExpireDate())) {
            status = Return.RETURN_LATE;
        }

        entity.returnBook(status, request.getReturnDate());

        LoanEntity savedEntity = repository.save(entity);
        return converter.toDto(savedEntity);
    }
}
