package com.digitalbooks.user.service;

import org.springframework.http.ResponseEntity;

import com.digitalbooks.user.entity.User;

public interface UserService {
    User registerUser(User u);
    ResponseEntity<?> allSubscribedBook(String emailId) ;
}
