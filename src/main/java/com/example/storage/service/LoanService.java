package com.example.storage.service;

import com.example.storage.domain.BookEntity;
import com.example.storage.domain.LoanEntity;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.dto.LoanReturnRequest;
import com.example.storage.enums.Loan;
import com.example.storage.enums.Return;
import com.example.storage.repository.BookRepository;
import com.example.storage.repository.LoanRepository;
import com.example.storage.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
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

    private final BookRepository bookRepository;
    private final UsersRepository usersRepository;

    public LoanService(LoanRepository repository, LoanConverter converter, BookRepository bookRepository, UsersRepository usersRepository) {
        super(repository, converter);
        this.bookRepository = bookRepository;
        this.usersRepository = usersRepository;
    }

    public List<LoanCRUDResponse> searchAllLoans() {
        List<LoanCRUDResponse> responses = new ArrayList<>();
        List<LoanEntity> entities = repository.findAll();
        for (LoanEntity entity : entities) {
            UsersEntity user = usersRepository.findById(entity.getUserId()).orElse(null);
            BookEntity book = bookRepository.findById(entity.getBookId()).orElse(null);
            if(user == null || book == null) {
                throw new IllegalArgumentException("user or book not found");
            }
            responses.add(converter.toDtoAll(entity, book, user));
        }
        return responses;
    }

    public List<LoanCRUDResponse> searchByBookId(Long bookId) {
        List<LoanCRUDResponse> responses = new ArrayList<>();
        log.info("searchByBookId: {}", bookId);
        List<LoanEntity> entities = repository.findAllByBookId(bookId);
        log.info("loans : {}", entities.size());
        for (LoanEntity entity : entities) {
            BookEntity book = bookRepository.findById(entity.getBookId()).orElse(null);
            if(book == null) {
                throw new IllegalArgumentException("Book not found");
            }
            log.info("add Book title : {}", book.getTitle());
            responses.add(converter.toDtoByBook(entity, book));
        }
        return responses;
    }

    public List<LoanCRUDResponse> searchByUserId(Long userId) {
        List<LoanEntity> entities = repository.findAllByUserId(userId);
        List<LoanCRUDResponse> responses = new ArrayList<>();
        log.info("loans : {}", entities.size());
        for (LoanEntity entity : entities) {
            BookEntity book = bookRepository.findById(entity.getBookId()).orElse(null);
            if(book == null) {
                throw new IllegalArgumentException("Book not found");
            }
            log.info("add Book title : {}", book.getTitle());
            responses.add(converter.toDtoByBook(entity, book));
        }
        return responses;
    }

    public LoanCRUDResponse loanBook(LoanCRUDRequest request) throws InstanceAlreadyExistsException {
        LoanEntity entity = converter.toEntity(request);

        if(repository.existsByBookIdAndUserIdAndWhetherReturn(entity.getBookId(), request.getUserId(), Return.LOANING))   {
            throw new InstanceAlreadyExistsException("이미 대출된 책은 반납 후 대출하시기 바랍니다.");
        }

        LoanEntity savedEntity = repository.save(entity);

        BookEntity book = bookRepository.findById(savedEntity.getBookId()).orElse(null);

        if(book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        book.updateStatus(Loan.LOAN_DISABLE);
        bookRepository.save(book);

        return converter.toDto(savedEntity);
    }

    public LoanCRUDResponse returnBook(LoanReturnRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("요청은 null값이 올 수 없습니다!");
        }

        LoanEntity entity = repository.findByBookIdAndUserIdAndWhetherReturn(request.getBookId(), request.getUserId(), Return.LOANING);

        if(entity == null) {
            throw new IllegalArgumentException("현재 대출중인 책이 아닙니다.");
        }

        Return status = Return.RETURNED;
        if(request.getReturnDate().isAfter(entity.getReturnExpireDate())) {
            status = Return.RETURN_LATE;
        }

        entity.returnBook(status, request.getReturnDate());

        LoanEntity savedEntity = repository.save(entity);

        BookEntity book = bookRepository.findById(savedEntity.getBookId()).orElse(null);

        if(book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        book.updateStatus(Loan.LOAN_ABLE);
        bookRepository.save(book);

        return converter.toDto(savedEntity);
    }
}
