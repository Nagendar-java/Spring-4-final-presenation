package com.digitalbooks.book.service;

import com.digitalbooks.book.entity.Book;
import com.digitalbooks.book.entity.UserResponse;
import com.digitalbooks.book.repository.BookRepository;


import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
@Transactional
@Slf4j
public class BookServiceImplementation implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImplementation.class);
    @Autowired
    BookRepository bookrepository;

    public BookServiceImplementation() {
    }

    public ResponseEntity<?> bookBlocking(Integer bookId, Integer AuthorId) {
    	 log.info("###BookServiceImplementation - Blocking####");
        this.bookrepository.bookStatusUpdate(bookId, AuthorId, false);
        return this.bookrepository.bookStatus(bookId, AuthorId).equals("false") ? new ResponseEntity("Updated Sucessfully!!", HttpStatus.OK) : new ResponseEntity("Failed to update!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> bookUnblocking(Integer bookId, Integer AuthorId) {
        Integer i = this.bookrepository.bookStatusUpdate(bookId, AuthorId, true);
        log.info("###BookServiceImplementation - Unblocking####" + i + " test : " + this.bookrepository.bookStatus(bookId, AuthorId));
        return this.bookrepository.bookStatus(bookId, AuthorId).equals("true") ? new ResponseEntity("Updated Sucessfully!!", HttpStatus.OK) : new ResponseEntity("Failed to update!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> bookCreation(Book book, Integer authorId) {
    	log.info("###BookServiceImplementation - BookCreation###");
        book.setBookAuthorId(authorId);
        return new ResponseEntity(this.bookrepository.save(book), HttpStatus.OK);
    }
    

    public ResponseEntity<?> bookUpdate(Book book, Integer authorId, Integer bookId) {
    	log.info("###BookServiceImplementation - BookUpdate###");
        return new ResponseEntity(this.bookrepository.save(book), HttpStatus.OK);
    }

    public ResponseEntity<?> searchBook(String category, String title, String author, Double price, String publisher) {
    	log.info("###BookServiceImplementation - SearchBook###");
        return this.bookrepository.bookSearchCount(category, title, author, price, publisher) >= 1 ? new ResponseEntity(this.bookrepository.bookSearch(category, title, author, price, publisher).toString(), HttpStatus.OK) : new ResponseEntity("No matching book found", HttpStatus.OK);
    }

    public ResponseEntity<?> bookFetchAllSubscribe(Integer bookAuthorId) {
    	log.info("###BookServiceImplementation - FetchAllSubscribeBook###");
        if (this.bookrepository.bookFetchCheck(bookAuthorId) != 0) {
        	UserResponse userResponse=new UserResponse();
            return new ResponseEntity(this.bookrepository.bookFetch(bookAuthorId).toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity("No Book match found with the provided details", HttpStatus.OK);
        }
    }

    public String bookSubscribing(Integer bookId) {
    	log.info("###BookServiceImplementation - bookSubscribing###");
    	Random random = new Random();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
    	this.bookrepository.bookSubscribe(bookId,formatted,LocalDate.now(),"yes");
        return "done";
    }

	@Override
	public ResponseEntity<?> bookFetchSubscribe(Integer authorId, String subscriptionId) {
		log.info("###BookServiceImplementation - FetchOneSubscribeBook###");
	        if (this.bookrepository.bookFetchCheck(authorId,subscriptionId) != 0) {
	            return new ResponseEntity(this.bookrepository.bookFetch(authorId,subscriptionId), HttpStatus.OK);
	        } else {
	            return new ResponseEntity("No Book match found with the provided details", HttpStatus.BAD_REQUEST);
	        }
	}

	@Override
	public ResponseEntity<?> cancelSubscriptionBook(Integer emailId, String subscriptionId) {
		// TODO Auto-generated method stub
		log.info("###BookServiceImplementation - cancelSubscriptionBook###");
		if (this.bookrepository.bookFetchCheck(emailId,subscriptionId) != 0) {
			long j=this.bookrepository.bookFetch(emailId,subscriptionId).getBookSubscribeDate().getTime();
			long difference_In_Time = new Date().getTime()-j;
			 long difference_In_hours= (difference_In_Time/	 (1000 * 60 * 60));
			 if(difference_In_hours<24) {
		  bookrepository.bookSubscribe(emailId,"xxxx",LocalDate.now(),"no");
            return new ResponseEntity<>("Subscription cancelled", HttpStatus.OK);
			 }
			 else {
				 return new ResponseEntity("You cant cancel the subscription now.Since 24hrs is crossed!!", HttpStatus.BAD_REQUEST);
			 }
        } else {
            return new ResponseEntity("No Book match found with the provided details", HttpStatus.BAD_REQUEST);
        }
	}
}
