package com.digitalbooks.user.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.digitalbooks.user.entity.BookCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bookId;
    private String bookTitle;
    private Integer bookAuthorId;
    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;
    private String bookPublisher;
    private Double bookPrice;
    private String bookContent;
    private Boolean bookStatus;
    private String bookSubscribe;
    private String bookSubscribeId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(style = "dd-mm-yyyy")
    private LocalDate bookReleaseDate;
    private String bookImage;
    
}
