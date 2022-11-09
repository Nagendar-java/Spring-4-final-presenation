

package com.digitalbooks.book.controller;

import com.digitalbooks.book.entity.Book;
import com.digitalbooks.book.entity.UserResponse;
import com.digitalbooks.book.repository.BookRepository;
import com.digitalbooks.book.service.BookService;


import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1/digitalbooks/"})
@CrossOrigin("*")
@Slf4j
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookService bookService;

    @Autowired
    private BookRepository bookRepository;
    public BookController() {
    }

    @GetMapping("/home")
    String welcomeMessage() {
        log.info("### Controller - Default Home Page ####");
        return "Welcome to Digital Book App!!!";
    }
    
    @GetMapping("/all")
    public java.util.List<Book> allBooks(){
    	return bookRepository.findAll();
    }
    
    @GetMapping("/book/{id}")
    public Optional<Book> viewBook(@PathVariable Integer id){
    	return bookRepository.findById(id);
    } 
    
    @PutMapping("/book/{id}")
    public Book editBook(@RequestBody Book b,@PathVariable Integer id){
    	b.setBookId(id);
    	return bookRepository.save(b);
    } 

    @PostMapping({"/author/{authorId}/book/{bookId}"})
    @ResponseBody
    public ResponseEntity<?> blockBook(@PathVariable Integer authorId, @PathVariable Integer bookId, @RequestParam("block") String status) {
        if (status.equals("yes")) {
            log.info("###BookController - A- Blocking Book ####");
            return this.bookService.bookBlocking(bookId, authorId);
        } else {
            log.info("###BookController - A- UnBlocking Book ####");
            return this.bookService.bookUnblocking(bookId, authorId);
        }
    }

    @PostMapping({"/author/{authorId}/books"})
    public ResponseEntity<?> createBook(@RequestBody Book book, @PathVariable Integer authorId) {
    	  log.info("###BookController - A- createBook####");
        return this.bookService.bookCreation(book, authorId);
    }
    @PostMapping({"/author/{authorId}/books/{bookId}"})
    public ResponseEntity<?> createBook(@RequestBody Book book, @PathVariable Integer authorId, @PathVariable Integer bookId) {
    	log.info("###BookController - A- editBook####");
    	return this.bookService.bookUpdate(book, authorId, bookId);
    }

    @GetMapping({"/search"})
    @ResponseBody
    public ResponseEntity<?> bookSearch(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("price") Double price, @RequestParam("publisher") String publisher) {
        log.info("###BookController - search####");
        return this.bookService.searchBook(category, title, author, price, publisher);
    }

    @ResponseBody
    @GetMapping({"/readers/{authorId}/books"})
    public ResponseEntity<?> fetchSubscribeBooks(@PathVariable Integer authorId) {
        log.info("###BookController - Fetch All Subscribe Books####");
        return new ResponseEntity(this.bookService.bookFetchAllSubscribe(authorId),HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping({"/readers/{authorId}/books/{subscriptionId}"})
    public ResponseEntity<?> fetchSubscribeBook(@PathVariable Integer authorId,@PathVariable String subscriptionId) {
        log.info("###BookController - Fetch one Subscribe Books ####");
        return this.bookService.bookFetchSubscribe(authorId,subscriptionId);
    }

    @PostMapping({"/{bookId}/subscribe"})
    ResponseEntity<?> subscribeBook(@PathVariable Integer bookId) {
        log.info("###BookController - Subscribing ####");
        return new ResponseEntity(this.bookService.bookSubscribing(bookId), HttpStatus.OK);
    }
    
    @PostMapping({"/readers/{emailId}/books/{subscriptionId}/cancel-subscription"})
    ResponseEntity<?> cancelSubscriptionBook(@PathVariable Integer emailId,@PathVariable String subscriptionId) {
        log.info("###BookController - cancelSubscriptionBook ####");
        return new ResponseEntity(this.bookService.cancelSubscriptionBook(emailId,subscriptionId), HttpStatus.OK);
    }
}
