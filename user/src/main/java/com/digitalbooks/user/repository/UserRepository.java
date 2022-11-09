package com.digitalbooks.user.repository;

import com.digitalbooks.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="select * from user where user_Email_Id=?1",nativeQuery = true)
    User findEmailId(String emailid);
    
    User findByUserEmailId(String s);
    
    @Query(value = "select user_Id from user where user_Email_Id=?1",nativeQuery = true)
    Integer findEmailIdByUserId(String emailid);
    
    
}
