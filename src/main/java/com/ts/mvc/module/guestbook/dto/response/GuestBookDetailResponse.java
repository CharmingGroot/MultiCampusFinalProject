package com.ts.mvc.module.guestbook.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ts.mvc.module.guestbook.GuestBook;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookDetailResponse {
	
	private Long gbIdx;
	private LocalDateTime regDate;
	private String user;
	private String content;
	
	public GuestBookDetailResponse(GuestBook guestBook) {
		this.gbIdx = guestBook.getGbIdx();
		this.regDate = guestBook.getRegDate();
		this.user = guestBook.getUser().getUserId();
		this.content = guestBook.getContent();
	}
	
	public String getRegDateAsDate() {
		return regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public String getRegDateAsTime() {
		return regDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

}
