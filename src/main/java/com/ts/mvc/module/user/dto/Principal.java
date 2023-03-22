package com.ts.mvc.module.user.dto;

import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Principal { // 인증이 끝난 정보를 담기 위한

	private String userId;
	private String password;
	private String email;
	private String nickname;
	private String profileImageUrl;
	private String grade;
	
	public Principal(User user) {
		this.userId = user.getUserId();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.nickname = user.getNickname();
		this.profileImageUrl = user.getProfileImageUrl();
		this.grade = user.getGrade();
	}
	
}
