package com.digitalbooks.user.service;

import com.digitalbooks.user.entity.Book;
import com.digitalbooks.user.entity.User;
import com.digitalbooks.user.entity.UserResponse;
import com.digitalbooks.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserServiceImplementation implements UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
	private RestTemplate restTemplate;

    public UserServiceImplementation() {
    }

    @Override
    public User registerUser(User u) {
        return userRepository.save(u);
    }

    public ResponseEntity<?> subscribeBook(String bookId) {
		// TODO Auto-generated method stub
    	log.info("###UserService subscribeBook####");
		String s=restTemplate.postForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/"+bookId+"/subscribe","", String.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}
    
    @Override
	public ResponseEntity<?> allSubscribedBook(String emailId) {
		// TODO Auto-generated method stub
    	log.info("###UserService allSubscribedBook###");
    	log.info("Email Id : "+emailId+" Author Id : "+userRepository.findEmailIdByUserId(emailId));
		Integer authorId=userRepository.findEmailIdByUserId(emailId);
		if(authorId!=null) {
			String s=restTemplate.getForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/readers/"+authorId+"/books", String.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not able find the user with the provided email",HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> subscribedBook(String emailId, String subscriptionId) {
		// TODO Auto-generated method stub
		log.info("###UserService subscribedBook###");
		Integer authorId=userRepository.findEmailIdByUserId(emailId);
		if(authorId!=null) {
			Book s=restTemplate.getForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/readers/"+authorId+"/books/"+subscriptionId, Book.class);
			return new ResponseEntity<>(s,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Not able find the user with the provided email",HttpStatus.BAD_REQUEST);
			}
	}

	public ResponseEntity<?> readSubscribedBook(String emailId, String subscriptionId) {
		log.info("####UserService readSubscribedBook####");
		Integer authorId=userRepository.findEmailIdByUserId(emailId);
		if(authorId!=null) {
			Book s=restTemplate.getForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/readers/"+authorId+"/books/"+subscriptionId, Book.class);
			return new ResponseEntity<>(s.getBookContent(),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>("Not able find the user with the provided email",HttpStatus.BAD_REQUEST);
			}
	}

	public ResponseEntity<?> searchBook(String category, String title, String author, Double price, String publisher) {
		// TODO Auto-generated method stub
		log.info("###UserService searchBook####");
		String s=restTemplate.getForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/search?category="+category+"&title="+title+"&author="+userRepository.findEmailIdByUserId(author)+"&price="+price+"&publisher="+publisher, String.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}

	public ResponseEntity<?> bookUpdate(Book book, Integer authorId, Integer bookId) {
		// TODO Auto-generated method stub
		log.info("###UserService bookUpdate####");
		Book s=restTemplate.postForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/author/"+authorId+"/books/"+bookId, book,Book.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}

	public ResponseEntity<?> bookCreation(Book book, Integer authorId) {
		// TODO Auto-generated method stub
		log.info("###UserService bookCreation###");
		Book s=restTemplate.postForObject("http://localhost:3333/api/v1/digitalbooks/author/"+authorId+"/books",book, Book.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}

	public ResponseEntity<?> bookUnblocking(Integer bookId, Integer authorId,String status) {
		// TODO Auto-generated method stub
		log.info("###UserService bookUnblocking###");
		String s=restTemplate.postForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/author/"+authorId+"/book/"+bookId+"?block="+status,"",String.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}

	public ResponseEntity<?> bookBlocking(Integer bookId, Integer authorId,String status) {
		// TODO Auto-generated method stub
		log.info("###UserService bookblocking###");

String s=restTemplate.postForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com/api/v1/digitalbooks/author/"+authorId+"/book/"+bookId+"?block="+status,"",String.class);
		return new ResponseEntity<>(s,HttpStatus.OK);
	}

	public ResponseEntity<?> cancelSubscribedBook(String emailId, String subscriptionId) {
		// TODO Auto-generated method stub
		log.info("###UserService cancelSubscribedBook###");
		Integer authorId=userRepository.findEmailIdByUserId(emailId);
		if(authorId!=null) {
		String s=restTemplate.postForObject("http://bookser-env.eba-kizcma9s.ap-northeast-1.elasticbeanstalk.com//api/v1/digitalbooks/readers/"+authorId+"/books/"+subscriptionId+"/cancel-subscription","",String.class);
				return new ResponseEntity<>(s,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not able find the user with the provided email",HttpStatus.BAD_REQUEST);
		}
	}    
}
