package com.ts.mvc.module.user;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ts.mvc.infra.code.Code;
import com.ts.mvc.infra.util.mail.EmailSender;
import com.ts.mvc.module.user.dto.Principal;
import com.ts.mvc.module.user.dto.request.LoginRequest;
import com.ts.mvc.module.user.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	private final EmailSender sender;
	private final PasswordEncoder passwordEncoder;

	public boolean existUser(String email) {
		return userRepository.existsById(email);
	}

	public void authenticateEmail(@Valid SignUpRequest form, String authToken) {
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("email", form.getEmail());
		body.put("authToken", authToken);
		body.put("mailTemplate", "signup-email-auth");
		
		RequestEntity<Map<String, Object>> request = 
				RequestEntity
				.post(Code.DOMAIN + "/mail")
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);
		
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		String html = response.getBody();
		
		sender.send(form.getEmail(), "회원가입을 환영합니다. 링크를 클릭해 회원가입을 완료하세요.", html);
	}

	@Transactional
	public void registNewMember(SignUpRequest form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		
		User user = User.createUser(form);
		
		userRepository.save(user);
	}

	public Principal authenticateUser(LoginRequest loginRequest) {
		User user = userRepository.findByEmailAndIsLeave(loginRequest.getEmail(), false);
		
		if(user == null) {
			return null;
		}
		
		if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			return null;
		}
		
		return new Principal(user);
	}
}
