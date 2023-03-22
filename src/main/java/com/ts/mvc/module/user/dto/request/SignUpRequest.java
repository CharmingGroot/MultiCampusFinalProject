package com.ts.mvc.module.user.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {

	@NotBlank
	private String userId;
	
	@NotBlank
	private String password;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nickname;
	
	private String grade;

	private String profileImageUrl;
}
