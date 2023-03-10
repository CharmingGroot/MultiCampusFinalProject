package com.ts.mvc.module.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UserRepository extends JpaRepository<User, String>{

	User findByEmailAndIsLeave(String email, boolean isLeave);

}
