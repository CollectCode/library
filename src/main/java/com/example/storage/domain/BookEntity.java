package com.example.storage.domain;

import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookDto;
import com.example.storage.enums.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	@Column(name = "book_title", columnDefinition = "VARCHAR(100)", nullable = false)
	private String title;
	
	@Column(name = "book_author", columnDefinition = "VARCHAR(50)", nullable = false)
	private String author;
	
	@Column(name = "book_publish", columnDefinition = "VARCHAR(50)")
	private String publish;
	
	@Column(name = "book_publish_date", columnDefinition = "date")
	private LocalDate publishDate;

	@Column(name = "book_img", columnDefinition = "VARCHAR(255)")
	private String bookImg;

	@Column(name = "book_price", columnDefinition = "int")
	private BigInteger price;

	@Enumerated(EnumType.STRING)
	@Column(name = "book_status")
	private Loan status;

	public BookEntity update(BookCRUDRequest request) {
		this.title = request.getTitle();
		this.author = request.getAuthor();
		this.publish = request.getPublish();
		this.publishDate = request.getPublishDate();
		this.bookImg = request.getBookImg();
		this.price = request.getPrice();
		this.status = request.getStatus();
		return this;
	}

	public void updateStatus(Loan status) {
		this.status = status;
	}
}