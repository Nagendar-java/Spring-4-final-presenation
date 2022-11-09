package com.digitalbooks.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitalbooks.user.entity.User;
import com.digitalbooks.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	 @Autowired
	    UserRepository userRepository;

	    public CustomUserDetailsService() {
	    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("############Username valdiation#########" + username);
        User u = this.userRepository.findEmailId(username);
        log.info("##returning user : "+u);
        if (u == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new CustomUserDetails(u);
        }
}
}
