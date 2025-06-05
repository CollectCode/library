package com.example.storage.domain;

import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookDto;
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
	
	@Column(name = "book_title", columnDefinition = "CHAR(100)", nullable = false)
	private String title;
	
	@Column(name = "book_author", columnDefinition = "CHAR(50)", nullable = false)
	private String author;
	
	@Column(name = "book_publish", columnDefinition = "CHAR(50)")
	private String publish;
	
	@Column(name = "book_publish_date", columnDefinition = "date")
	private LocalDate publishDate;

	@Column(name = "book_img", columnDefinition = "VARCHAR(255)")
	private String bookImg;

	@Column(name = "book_price", columnDefinition = "int")
	private BigInteger price;

	public BookEntity update(BookCRUDRequest request) {
		BookDto dto = request.getBook();
		this.title = dto.getTitle();
		this.author = dto.getAuthor();
		this.publish = dto.getPublish();
		this.publishDate = dto.getPublishDate();
		this.bookImg = dto.getBookImg();
		this.price = dto.getPrice();
		return this;
	}
}