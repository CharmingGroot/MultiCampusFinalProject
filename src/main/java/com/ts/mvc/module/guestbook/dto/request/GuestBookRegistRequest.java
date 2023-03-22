package com.ts.mvc.module.guestbook.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookRegistRequest {

	private String userId;
	
	@NotEmpty
	private String content;
}
