package com.ts.mvc.module.chat.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatResponse {
	
	private String message;
	private String role;
	private Long created;


}
