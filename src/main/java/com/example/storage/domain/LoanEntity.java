package com.example.storage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
@IdClass(UserBookId.class)
public class LoanEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Column(name = "loan_date", columnDefinition = "DATE", nullable = false)
    private LocalDate loanDate;

    @Column(name = "return_date", columnDefinition = "DATE", nullable = false)
    private LocalDate returnDate;

    @Column(name = "whether_return", columnDefinition = "CHAR(10)", nullable = false)
    private String whetherReturn;
}
