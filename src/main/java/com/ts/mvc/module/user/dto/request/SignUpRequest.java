package com.ts.mvc.module.user.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String tell;
	
}
