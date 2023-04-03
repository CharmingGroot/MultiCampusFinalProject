package com.ts.mvc.module.guestbook.dto;

import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageOwnerDTO {
	
	private String user; // 게스트북 페이지 정보
	private boolean isPageOwner; // 페이지 소유권 여부
	private boolean isPageVisitor; // 페이지 방문자 여부
	private boolean canDeleteAll; // 모든 삭제권한
	private boolean canModify; // 작성글 수정권한
	private boolean canDelete; // 작성글 삭제권한
	
	
}
