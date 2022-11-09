package com.digitalbooks.book.repository;

import com.digitalbooks.book.entity.Book;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.util.MultiValueMap;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query(value = "UPDATE book SET book_status=?3 WHERE book_id=?1 and book_author_id=?2",nativeQuery = true)
    Integer bookStatusUpdate(Integer bookid, Integer authorid, boolean status);

    @Query(value = "select book_status from book where book_id=?1 and book_author_id=?2",nativeQuery = true)
    String bookStatus(Integer bookid, Integer authorid);

    @Query(value = "select * from book where book_category=?1 and book_title=?2 and book_author_id=?3 and book_price=?4 and book_publisher=?5",nativeQuery = true)
    Book bookSearch(String category, String title, String author, Double price, String publisher);

    @Query(value = "select count(*) from book where book_category=?1 and book_title=?2 and book_author_id=?3 and book_price=?4 and book_publisher=?5",nativeQuery = true)
    Integer bookSearchCount(String category, String title, String author, Double price, String publisher);

    @Query(value = "select count(*) from book where book_author_id=?1",nativeQuery = true)
    Integer bookFetchCheck(Integer Id);

    @Query(value = "select * from book where book_author_id=?1",nativeQuery = true)
    List<Book> bookFetch(Integer Id);

    @Query(value = "select * from book where book_Id=?1 and book_author_id=?2",nativeQuery = true)
    Book bookFind(Integer bookid, Integer authorid);
    
    @Query(value = "select count(*) from book where book_author_id=?1 and book_subscribe_id=?2 and book_subscribe='yes'",nativeQuery = true)
	Integer bookFetchCheck(Integer authorId, String subscriptionId);
    
	 @Query(value = "select * from book where book_author_id=?1 and book_subscribe_id=?2 and book_subscribe='yes'",nativeQuery = true)
	Book bookFetch(Integer authorId, String subscriptionId);
     
	 @Modifying
	 @Query(value = "UPDATE book SET book_subscribe=?4,book_subscribe_id=?2,book_subscribe_date=?3 WHERE book_id=?1",nativeQuery = true)
	void bookSubscribe(Integer bookId, String formatted, LocalDate localDate,String status);
}
