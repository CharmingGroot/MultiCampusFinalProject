package com.ts.mvc.module.user.api.dto;

import com.ts.mvc.module.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	private String password;
	private String nickname;
	
	public User toEntity() {
		return User.builder()
				.password(password)
				.nickname(nickname)
				.build();
	}
}
