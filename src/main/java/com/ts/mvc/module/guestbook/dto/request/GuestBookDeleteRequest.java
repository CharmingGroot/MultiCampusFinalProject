package com.ts.mvc.module.guestbook.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestBookDeleteRequest {

	private Long gbIdx;

	public GuestBookDeleteRequest(Long gbIdx) {
		this.gbIdx = gbIdx;
	}
	
	
	
}
