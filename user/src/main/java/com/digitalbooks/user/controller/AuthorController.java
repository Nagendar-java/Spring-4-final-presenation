package com.digitalbooks.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.user.config.jwtUtils;
import com.digitalbooks.user.entity.Book;
import com.digitalbooks.user.service.UserServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class AuthorController {

	    @Autowired
	    UserServiceImplementation userServiceImplementation;
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    public AuthorController() {
	    }
	    
	    @PreAuthorize("hasRole('AUTHOR')")
	    @GetMapping({"/homeA"})
	    public String homePage() {
		  // log.info();
	        log.info("############Welcome page of user microservice#########");
	        return "Welcome to Author Page!!";
	    }
	
	// @PreAuthorize("hasRole('AUTHOR')")
	   @PostMapping({"/api/v1/digitalbooks/author/{authorId}/books"})
	   public ResponseEntity<?> createBook(@RequestBody Book book, @PathVariable Integer authorId) {
		   log.info("###UserController -A- createBook####");
	       return this.userServiceImplementation.bookCreation(book, authorId);
	   }

	   @PreAuthorize("hasRole('AUTHOR')")
	   @PutMapping({"/api/v1/digitalbooks/author/{authorId}/books/{bookId}"})
	   public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Integer authorId, @PathVariable Integer bookId) {
		   log.info("###UserController -A- updateBook####");
		   return this.userServiceImplementation.bookUpdate(book, authorId, bookId);
	   }   
	
	   @PostMapping({"/api/v1/digitalbooks/author/{authorId}/book/{bookId}"})
	   @ResponseBody
	   public ResponseEntity<?> blockBook(@PathVariable Integer authorId, @PathVariable Integer bookId, @RequestParam("block") String status) {
	       if (status.equals("yes")) {
	           log.info("###UserController -A- Blocking Book####");
	           return this.userServiceImplementation.bookBlocking(bookId, authorId,status);
	       } else {
	           log.info("###UserController -A- UnBlocking Book####");
	           return this.userServiceImplementation.bookUnblocking(bookId, authorId,status);
	       }
	   }
}
