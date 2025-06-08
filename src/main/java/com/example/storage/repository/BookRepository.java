package com.example.storage.repository;

import com.example.storage.domain.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByTitle(String title);
    List<BookEntity> findAllByAuthor(String author);
    List<BookEntity> findAllByPublish(String publish);
}
