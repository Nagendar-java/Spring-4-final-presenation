package com.digitalbooks.user.controller;

import com.digitalbooks.user.config.jwtUtils;
import com.digitalbooks.user.entity.AuthRequest;
import com.digitalbooks.user.entity.Book;
import com.digitalbooks.user.entity.User;
import com.digitalbooks.user.repository.UserRepository;
import com.digitalbooks.user.service.UserServiceImplementation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin("*")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserServiceImplementation userServiceImplementation;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private jwtUtils jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserController() {
    }
    
    

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
    	log.info("Authentication BLock###");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return "Invalid username/password";
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
     
//   @PreAuthorize("hasRole('READER')")
   @GetMapping({"/homeR"})
   public String homePage1() {
	  // log.info();
       log.info("############Welcome page of user microservice#########");
       return "Welcome to reader page!!";
   }
   
   @GetMapping({"/api/v1/digitalbooks/search"})
   @ResponseBody
   public ResponseEntity<?> bookSearch(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("price") Double price, @RequestParam("publisher") String publisher) {
       log.info("###UserController -G-A-R- Search####");
       return this.userServiceImplementation.searchBook(category, title, author, price, publisher);
   }
   
   @PostMapping({"/api/v1/digitalbooks/signup"})
   public User registerUser(@RequestBody User u) {
       log.info("############UserController Signup#########");
       return this.userServiceImplementation.registerUser(u);
   }

   @PostMapping({"/api/v1/digitalbooks/signin"})
   public ResponseEntity<?>  loginUser(@RequestBody User u) {
      // log.info("############UserController Login#########"+u.toString()+":"+u.getUserEmailId()+":"+userRepository.findEmailId(u.getUserEmailId()));
       User uData=userRepository.findEmailId(u.getUserEmailId());
       log.info("UserController :: loginUser :: {}",uData.toString());
		if(uData.getPassword().equals(uData.getPassword()) ){
			log.info("u data"+u.toString()); 
			return ResponseEntity.ok(uData);
		}else {log.info("u error####");
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
		}
   }     
}
