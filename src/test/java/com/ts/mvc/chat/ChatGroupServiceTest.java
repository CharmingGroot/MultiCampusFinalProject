package com.ts.mvc.chat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ts.mvc.module.group.ChatGroup;
import com.ts.mvc.module.group.ChatGroupRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;

@SpringBootTest
public class ChatGroupServiceTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChatGroupRepository chatGroupRespository;
	
	@Test
	public void testCreateChatGroup() {
		
		User group1A = userRepository.findById("group1A").get();
		User group1B = userRepository.findById("group1B").get();
		User group2A = userRepository.findById("group2A").get();
		User group2B = userRepository.findById("group2B").get();
		
		ChatGroup group1 = new ChatGroup();
		ChatGroup group2 = new ChatGroup();
		
		group1.addUsers(group1A);
		group1.addUsers(group1B);
		
		group2.addUsers(group2A);
		group2.addUsers(group2B);
		
		chatGroupRespository.save(group1);
		chatGroupRespository.save(group2);
	

	}
}	
