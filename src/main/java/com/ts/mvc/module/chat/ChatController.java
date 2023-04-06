package com.ts.mvc.module.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ts.mvc.module.chat.dto.request.ChatRequest;

@Controller
@RequiredArgsConstructor
public class ChatController {
    
	private final ChatService chatService;
	private final SimpMessagingTemplate simpMessagingTemplate;

	 @GetMapping("/welcome/chat")
	 public String chat(Model model) {
		 
		 Long cgIdx = chatService.findChatGroupIdxByUserId();
		 model.addAttribute("cgIdx",cgIdx);
		 return "/welcome/chat";
	 }
	
	  @MessageMapping("/chat-room")
	  public void chatting(ChatRequest dto) throws Exception {
	      simpMessagingTemplate.convertAndSend("/topic/chatting", dto);
	  }
}