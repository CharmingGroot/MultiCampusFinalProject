package com.ts.mvc.module.chat.dto.request;

import com.ts.mvc.module.user.dto.Principal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatRequest {
	
	private String message;
	private String targetUserId;

	

}
