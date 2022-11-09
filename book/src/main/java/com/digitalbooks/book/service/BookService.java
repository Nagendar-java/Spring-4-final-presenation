package com.digitalbooks.book.service;

import com.digitalbooks.book.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface BookService {
    ResponseEntity<?> searchBook(String category, String title, String author, Double price, String publisher);

    ResponseEntity<?> bookFetchAllSubscribe(Integer bookAuthorId);

    ResponseEntity<?> bookCreation(Book book, Integer authorId);

    ResponseEntity<?> bookBlocking(Integer bookId, Integer AuthorId);

    ResponseEntity<?> bookUnblocking(Integer bookId, Integer AuthorId);

    String bookSubscribing(Integer bookId);

    ResponseEntity<?> bookUpdate(Book book, Integer authorId, Integer bookId);

	ResponseEntity<?> bookFetchSubscribe(Integer authorId, String subscriptionId);

	ResponseEntity<?> cancelSubscriptionBook(Integer emailId, String subscriptionId);
}
