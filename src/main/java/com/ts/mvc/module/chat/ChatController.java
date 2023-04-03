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
		 
		 String chatmember = chatService.findChatMemeberByUserId();
		 model.addAttribute("chatMember",chatmember);
		 return "/welcome/chat";
	 }
	
	  @MessageMapping("/chat-room/{chatmember}")
	  public void chatting(@DestinationVariable("chatmember") String chatmember, ChatRequest dto) throws Exception {
	      simpMessagingTemplate.convertAndSend("/topic/chatting/" + chatmember, chatService.sendMessage(dto));
	  }
}