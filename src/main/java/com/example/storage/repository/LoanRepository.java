package com.example.storage.repository;

import com.example.storage.domain.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByBookId(Long bookId);
    List<LoanEntity> findAllByUserId(Long userId);
}
