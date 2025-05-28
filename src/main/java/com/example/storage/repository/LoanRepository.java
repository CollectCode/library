package com.example.storage.repository;

import com.example.storage.domain.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepository extends JpaRepository<LoanEntity, Long> {
}
