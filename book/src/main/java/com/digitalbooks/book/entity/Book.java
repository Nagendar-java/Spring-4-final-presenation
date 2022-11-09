package com.digitalbooks.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer bookId;
    private String bookTitle;
    @NotNull
    @Column(name = "bookAuthorId")
    private Integer bookAuthorId;
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;
    private String bookPublisher;
    private Double bookPrice;
    private String bookContent;
    private Boolean bookStatus;
    private String bookSubscribe;
    private Date bookSubscribeDate;
    private String bookSubscribeId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(style = "dd-mm-yyyy")
    private LocalDate bookReleaseDate;
    private String bookImage;
	public Book(Integer bookId, String bookTitle, @NotNull Integer bookAuthorId, BookCategory bookCategory,
			String bookPublisher, Double bookPrice, String bookContent, Boolean bookStatus, LocalDate bookReleaseDate,
			String bookImage) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthorId = bookAuthorId;
		this.bookCategory = bookCategory;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.bookContent = bookContent;
		this.bookStatus = bookStatus;
		this.bookReleaseDate = bookReleaseDate;
		this.bookImage = bookImage;
	}
	public Book(Integer bookId, String bookTitle, @NotNull Integer bookAuthorId, BookCategory bookCategory,
			String bookPublisher, Double bookPrice, String bookContent, Boolean bookStatus, String bookSubscribe,
			Date bookSubscribeDate, String bookSubscribeId, LocalDate bookReleaseDate, String bookImage) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthorId = bookAuthorId;
		this.bookCategory = bookCategory;
		this.bookPublisher = bookPublisher;
		this.bookPrice = bookPrice;
		this.bookContent = bookContent;
		this.bookStatus = bookStatus;
		this.bookSubscribe = bookSubscribe;
		this.bookSubscribeDate = bookSubscribeDate;
		this.bookSubscribeId = bookSubscribeId;
		this.bookReleaseDate = bookReleaseDate;
		this.bookImage = bookImage;
	}
	
	
}
