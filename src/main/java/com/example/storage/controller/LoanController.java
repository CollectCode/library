package com.example.storage.controller;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.dto.LoanReturnRequest;
import com.example.storage.repository.LoanRepository;
import com.example.storage.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController extends AbsController<
        LoanCRUDRequest,
        LoanCRUDResponse,
        LoanEntity,
        Long,
        LoanConverter,
        LoanRepository,
        LoanService> {

    public LoanController(LoanService service) {
        super(service);
    }

    //TODO 책 ID를 통한 대출 현황 및 이력을 조회
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanCRUDResponse>> readLoansByBookId(
            @PathVariable Long bookId
    ) {
        List<LoanCRUDResponse> responses = service.searchByBookId(bookId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    //TODO 유저 ID를 통한 대출 현황 및 이력을 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanCRUDResponse>> readLoansByUserId(
            @PathVariable Long userId
    )   {
        List<LoanCRUDResponse> responses = service.searchByUserId(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    //TODO 책 대출 요청
    @PostMapping
    public ResponseEntity<LoanCRUDResponse> addLoan(@RequestBody LoanCRUDRequest request) throws InstanceAlreadyExistsException {
        LoanCRUDResponse response = service.loanBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //TODO 책 반납 요청
    @PutMapping("/return")
    public ResponseEntity<LoanCRUDResponse> delete(@RequestBody LoanReturnRequest request) {
        LoanCRUDResponse response = service.returnBook(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
