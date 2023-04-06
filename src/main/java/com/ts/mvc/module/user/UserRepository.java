package com.ts.mvc.module.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.user.dto.Principal;

@Repository 
public interface UserRepository extends JpaRepository<User, String>{

	User findByUserId(String userId);


}
