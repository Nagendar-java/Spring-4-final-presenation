package com.digitalbooks.book;

import com.digitalbooks.book.entity.Book;
import com.digitalbooks.book.entity.BookCategory;
import com.digitalbooks.book.repository.BookRepository;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(BookApplication.class);
    @Autowired
    BookRepository bookRepository;

    public BookApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    public void run(String... args) throws Exception {
        Book book = new Book(1, "Title Name", 1, BookCategory.ADVENTURE, "Publisher", 500.0D, "Content", true,"yes",new Date(),"123a" ,LocalDate.of(2022, 2, 10), "qwew");
        Book book1 = new Book(2, "one grilfriend", 2, BookCategory.SUSPENSE, "ram studios", 3000.0D, "the story is good", true, LocalDate.of(1998, 2, 10), "String bookImage");
        Book book2 = new Book(3, "the dawn", 2, BookCategory.DRAMA, "sita studios", 5000.0D, "the story is good 1", false, LocalDate.of(2000, 2, 10), "String bookImage1");
        this.bookRepository.save(book);
        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        log.info("###Created user table and inserted 3 rows!!!###");
    }
}
