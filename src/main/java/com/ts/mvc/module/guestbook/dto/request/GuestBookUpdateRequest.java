package com.ts.mvc.module.guestbook.dto.request;

import javax.persistence.ManyToOne;

import com.ts.mvc.module.guestbook.GuestBook;
import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookUpdateRequest {

	@ManyToOne
	private User user;
	
	private Long gbIdx;
	
	private String content;

	
	public GuestBookUpdateRequest(GuestBook entity) {
		this.gbIdx = entity.getGbIdx();
		this.user = entity.getUser();
		this.content = entity.getContent();
	}
	
}
