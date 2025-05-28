package com.example.storage.controller;

import com.example.storage.domain.LoanEntity;
import com.example.storage.dto.LoanCRUDRequest;
import com.example.storage.dto.LoanCRUDResponse;
import com.example.storage.converter.LoanConverter;
import com.example.storage.repository.LoanRepository;
import com.example.storage.service.LoanService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //TODO 유저 ID를 통한 대출 현황 및 이력을 조회

    //TODO 책 대출 요청

    //TODO 책 반납 요청
}
