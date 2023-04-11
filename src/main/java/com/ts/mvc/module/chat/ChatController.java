package com.ts.mvc.module.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ts.mvc.module.chat.dto.request.ChatRequest;
import com.ts.mvc.module.chat.dto.response.ChatResponse;
import com.ts.mvc.module.user.dto.Principal;

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

	 

	 
//	 @MessageMapping("/chatting/{cgIdx}")
//	 public void message(ChatRequest message, @DestinationVariable Long cgIdx, Principal principal) {
//		 cgIdx = chatService.findChatGroupIdxByUserId();
//		 System.out.println(cgIdx);
//	     simpMessagingTemplate.convertAndSend("/topic/chatting/" + cgIdx, message);
//	 }

}