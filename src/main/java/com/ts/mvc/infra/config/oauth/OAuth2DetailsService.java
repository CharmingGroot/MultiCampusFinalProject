package com.ts.mvc.infra.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.ts.mvc.infra.code.Role;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.dto.Principal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		//  System.out.println(oAuth2User.getAttributes());
		
		Map<String, Object> userInfo = oAuth2User.getAttributes();
		String userId = "google_" + (String) userInfo.get("sub");
		String password = passwordEncoder.encode(UUID.randomUUID().toString());
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("name");
		String profileImageUrl = (String) userInfo.get("picture");
		String grade = Role.USER.desc();		
		
		User userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) { // 구글로 최초 로그인
			User user = User.builder()
					.userId(userId)
					.password(password)
					.email(email)
					.nickname(nickname)
					.profileImageUrl(profileImageUrl)
					.grade(grade)
					.build();
			
			return new UserPrincipal(new Principal(userRepository.save(user)));
			
		} else { // 이미 회원가입 함
			return new UserPrincipal(new Principal(userEntity));
		}
	
	}
}
