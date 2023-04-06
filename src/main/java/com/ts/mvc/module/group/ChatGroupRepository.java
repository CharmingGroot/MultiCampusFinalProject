package com.ts.mvc.module.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup, Long>{
	
	ChatGroup findChatGroupByUsersUserId(String userId);
	
}
