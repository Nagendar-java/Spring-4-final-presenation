package com.digitalbooks.user.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.digitalbooks.user.config.jwtFilter;
import com.digitalbooks.user.service.CustomUserDetails;
import com.digitalbooks.user.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class UserConfiguration extends WebSecurityConfigurerAdapter{

	 @Autowired
	 CustomUserDetailsService userDetailsService;
	 
	 @Autowired
	  jwtFilter jwtFilter;

	    public UserConfiguration() {
	    }

	    @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		   auth.userDetailsService(this.userDetailsService);
	   }
	    
	    @Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	    /*public AuthenticationProvider AuthenticationProvider() {
	        DaoAuthenticationProvider d = new DaoAuthenticationProvider();
	        d.setUserDetailsService(this.userDetailsService);
	        d.setPasswordEncoder(new BCryptPasswordEncoder());
	        log.info("getting crpt vales"+d);
	        return d;
	    }*/
	    
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	        .antMatchers("/authenticate").permitAll()
	        .antMatchers("/api/v1/digitalbooks/signup").permitAll()
	        .antMatchers("/api/v1/digitalbooks/signin").permitAll()
	                .anyRequest().authenticated()
	                .and().exceptionHandling().and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
	    }
	    
	    /*protected void configure(HttpSecurity http) throws Exception {
	    	http.csrf().disable().authorizeRequests()
	    	.antMatchers("/login").permitAll()
	    	.antMatchers("/home").permitAll()
	    	//hasAnyRole("AUTHOR")
	    	.anyRequest().authenticated()
	    	.and()
	    	.formLogin().loginPage("/login").permitAll();;
	    	//.and().httpBasic();
	    	//.and()
	    	//.formLogin().loginPage("/login").permitAll();
	    	
	        /*((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((HttpSecurity)http.csrf().disable()).
	        		authorizeRequests().antMatchers(new String[]{"/css/style.css"})).permitAll().antMatchers(new String[]{"/api/v1/digitalbooks"})).permitAll().anyRequest()).permitAll();
	   }
	
	/*@Bean
	protected UserDetailsService userDetailsService() {
		 
		  List<UserDetails> users =new ArrayList<>();
		  users.add(User.withDefaultPasswordEncoder().username("poojitha").password("1234").roles("USER").build());
		 
		  return new InMemoryUserDetailsManager(users);		  
		  }*/
}