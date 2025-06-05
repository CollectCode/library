package com.example.storage.domain;

import com.example.storage.enums.Return;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LoanId;

    @Column(name = "user_id", columnDefinition = "INT", nullable = false)
    private Long userId;

    @Column(name = "book_id", columnDefinition = "INT", nullable = false)
    private Long bookId;

    @Column(name = "loan_date", columnDefinition = "DATE", nullable = false)
    private LocalDate loanDate;

    @Column(name = "return_expire_date", columnDefinition = "DATE", nullable = false)
    private LocalDate returnExpireDate;

    @Column(name = "returned_date", columnDefinition = "DATE")
    private LocalDate returnedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "whether_return", nullable = false)
    private Return whetherReturn;

    public void updateReturnType(Return returnType)  {
        if(returnType == null)  {
            throw new IllegalArgumentException("Return type can not be null");
        }
        this.whetherReturn = returnType;
    }

    public void updateReturnDate(LocalDate returnDate)  {
        if(returnDate == null)  {
            throw new IllegalArgumentException("Return date can not be null");
        }
        this.returnedDate = returnDate;
    }

    public void returnBook(Return returnType, LocalDate returnDate)    {
        updateReturnType(returnType);
        updateReturnDate(returnDate);
    }
}
