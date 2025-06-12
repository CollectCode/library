package com.example.storage.repository;

import com.example.storage.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> findAllByTitle(String title, Pageable pageable);
    Page<BookEntity> findAllByAuthor(String author, Pageable pageable);
    Page<BookEntity> findAllByPublish(String publish, Pageable pageable);
}
