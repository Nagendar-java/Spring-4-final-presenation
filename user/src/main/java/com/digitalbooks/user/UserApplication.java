package com.digitalbooks.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.digitalbooks.user.entity.User;
import com.digitalbooks.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class UserApplication implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
	
	@Bean
	RestTemplate restTempalte() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User u=new User(1,"jhon","jhon@gmail.com","1234","ROLE_AUTHOR");
		User u1=new User(2,"michel","michel@gmail.com","$2a$12$eS1N6DdtWkArwW8DhSlQQ.Lv25H6NVuOiL4HUw71oR1OfoVGzh5Ce","ROLE_READER");
		User u2=new User(3,"clark","clark@gmail.com","1234","ROLE_READER");
		User u3=new User(4,"simmonds","simmonds@gmail.com","$2a$12$eS1N6DdtWkArwW8DhSlQQ.Lv25H6NVuOiL4HUw71oR1OfoVGzh5Ce","ROLE_AUTHOR");
		userRepository.save(u);
		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);
	} 
}
