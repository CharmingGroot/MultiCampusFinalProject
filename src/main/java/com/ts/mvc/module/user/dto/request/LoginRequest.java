package com.ts.mvc.module.user.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message="이메일을 입력해주세요.")
	private String email;
	
	@NotBlank(message="비밀번호를 입력해주세요")
	private String password;
	
}
