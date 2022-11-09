package com.digitalbooks.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.user.config.jwtUtils;
import com.digitalbooks.user.service.UserServiceImplementation;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
public class ReaderController {
	@Autowired
    UserServiceImplementation userServiceImplementation;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReaderController() {
    }

	 @PreAuthorize("hasRole('READER')")
	   @PostMapping({"/api/v1/digitalbooks/{bookId}/subscribe"})
	   public ResponseEntity<?> subscribeBook(@PathVariable String bookId) {
	       log.info("############UserController -R- subscribeBook get#########");
	       return this.userServiceImplementation.subscribeBook(bookId);
	   }
	   
	   @PreAuthorize("hasRole('READER')")
	    @GetMapping({"/api/v1/digitalbooks/readers/{emailId}/books"})
	    public ResponseEntity<?> allSubscribedBook(@PathVariable String emailId) {
	        log.info("############UserController -R- Fetch All Subscribed books#########");
	        return this.userServiceImplementation.allSubscribedBook(emailId);
	    }
	    
	   @PreAuthorize("hasRole('READER')")
	    @GetMapping({"/api/v1/digitalbooks/readers/{emailId}/books/{subscriptionId}"})
	    public ResponseEntity<?> subscribedBook(@PathVariable String emailId,@PathVariable String subscriptionId) {
	        log.info("############UserController -R- Fetch Subscribed book#########");
	        return this.userServiceImplementation.subscribedBook(emailId,subscriptionId);
	    }
	    
	   @PreAuthorize("hasRole('READER')")
	    @GetMapping({"/api/v1/digitalbooks/readers/{emailId}/books/{subscriptionId}/read"})
	    public ResponseEntity<?> readSubscribedBook(@PathVariable String emailId,@PathVariable String subscriptionId) {
	        log.info("############UserController -R- readSubscribedBook#########");
	        return this.userServiceImplementation.readSubscribedBook(emailId,subscriptionId);
	    }
	
	   @PreAuthorize("hasRole('READER')")
	    @PostMapping({"/api/v1/digitalbooks/readers/{emailId}/books/{subscriptionId}/cancel-subscription"})
	    public ResponseEntity<?> cancelSubscribedBook(@PathVariable String emailId,@PathVariable String subscriptionId) {
	        log.info("############UserController -R- cancelSubscribedBook#########");
	        return this.userServiceImplementation.cancelSubscribedBook(emailId,subscriptionId);
	    }
}
