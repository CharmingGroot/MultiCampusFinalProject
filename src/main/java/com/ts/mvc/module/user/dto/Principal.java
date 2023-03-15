package com.ts.mvc.module.user.dto;

import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Principal { // 인증이 끝난 정보를 담기 위한

	private String email;
	private String userName;
	private String password;
	private String tell;
	private String grade;
	
	public Principal(User user) {
		this.email = user.getEmail();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.tell = user.getPassword();
		this.grade = user.getGrade();
	}
	
}
