package com.digitalbooks.user.repository;

import com.digitalbooks.user.entity.UserRoleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDetailsRepository extends JpaRepository<UserRoleDetails, Integer> {
}
