package com.ts.mvc.module.user;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ts.mvc.infra.code.Code;
import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.infra.util.mail.EmailSender;
import com.ts.mvc.module.user.dto.Principal;
import com.ts.mvc.module.user.dto.request.LoginRequest;
import com.ts.mvc.module.user.dto.request.SignUpRequest;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final RestTemplate restTemplate;
	private final EmailSender sender;
	private final PasswordEncoder passwordEncoder;

	public boolean existUser(String userId) {
		return userRepository.existsById(userId);
	}

	public void authenticateEmail(@Valid SignUpRequest form, String authToken) {
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("userId", form.getUserId());
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
	
	@Transactional
	public User modifyUser(String userId, User user) {
		// 1. 영속화
		User userEntity = userRepository.findById(userId).get();
		
		// 2. 영속화된 오브젝트를 수정 - 더티체킹 (업데이트 완료)
		userEntity.setNickname(user.getNickname());
		
		String rawPassword = user.getPassword();
		String encPassword = passwordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		
		return userEntity;
	}

	// 1. 패스워드는 알아서 체킹
	// 2. 리턴이 잘되면 자동으로 세션을 만든다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserId(username);
		if(user == null) throw new UsernameNotFoundException(username);
		
		return new UserPrincipal(user);
	}

	@Transactional
	public User userProfileImageModify(UserPrincipal userPrincipal, MultipartFile profileImageFile) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
//		System.out.println("이미지 파일이름: " + imageFileName);
		
//		Path imageFilePath = Paths.get(uploadFoler + imageFileName);
		return null;
	}
	

}
