package com.ts.mvc.module.guestbook.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.ts.mvc.module.guestbook.GuestBook;
import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookListResponse {

	private Long gbIdx;
	private User user;
	private User host;
	private String content;
	
	public GuestBookListResponse(GuestBook entity) {
		this.gbIdx = entity.getGbIdx();
		this.user = entity.getUser();
		this.host = entity.getHost();
		this.content = entity.getContent();
	}
	
	public static List<GuestBookListResponse> toDtoList(List<GuestBook> entityList){
		return entityList.stream().map(e -> new GuestBookListResponse(e)).collect(Collectors.toList());
	}
}
