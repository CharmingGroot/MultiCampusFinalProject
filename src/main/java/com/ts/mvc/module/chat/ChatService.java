package com.ts.mvc.module.chat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.chat.dto.request.ChatRequest;
import com.ts.mvc.module.group.ChatGroup;
import com.ts.mvc.module.group.ChatGroupRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {
	
	private final ChatGroupRepository chatGroupRepository;
	
	public String sendMessage(ChatRequest dto) {
		
		String chat = dto.getMessage();
		return chat;
	}

	public Long findChatGroupIdxByUserId() {
		ChatGroup chatGroup = chatGroupRepository.findChatGroupByUsersUserId(UserPrincipal.getUserPrincipal().getUserId());
		return chatGroup.getCgIdx();
	}


	
}
