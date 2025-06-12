package com.example.storage.repository;

import com.example.storage.domain.LoanEntity;
import com.example.storage.enums.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByBookId(Long bookId);
    List<LoanEntity> findAllByUserId(Long userId);

    LoanEntity findByBookIdAndUserIdAndWhetherReturn(Long bookId, Long userId, Return whetherReturn);
    boolean existsByBookIdAndUserIdAndWhetherReturn(Long bookId, Long userId, Return whetherReturn);
}
