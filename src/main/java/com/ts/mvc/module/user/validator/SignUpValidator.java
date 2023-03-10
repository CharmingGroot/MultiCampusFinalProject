package com.ts.mvc.module.user.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SignUpValidator implements Validator{
	
	private UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignUpRequest form = (SignUpRequest) target;
		
		if(userRepository.existsById(form.getEmail())) {
			errors.rejectValue("email", "error.email","이미 존재하는 이메일 입니다.");
		}
		
		//비밀번호가 8글자 이상의 숫자, 영문자, 특수문자 조합인지 확인
		if(!Pattern.compile("(?!.*[ㄱ-힣])(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9ㄱ-힣])(?=.{8,})").matcher(form.getPassword()).find()) {
			errors.rejectValue("password", "password.format", "비밀번호는 영문, 숫자, 특수문자 조합의 8자리 이상의 문자열입니다.");
		}
	}
	
	
}
