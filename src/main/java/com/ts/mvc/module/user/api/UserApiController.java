package com.ts.mvc.module.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserService;
import com.ts.mvc.module.user.api.dto.UserUpdateDto;
import com.ts.mvc.module.user.dto.Principal;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {
	
	private final UserService userService;
	
	@PutMapping("/api/user")
	public CMRespDto<?> update(@AuthenticationPrincipal UserPrincipal userPrincipal, UserUpdateDto userUpdateDto) {
//		System.out.println(userUpdateDto);
		
		System.out.println(userUpdateDto);
		
		User userEntity = userService.modifyUser(userPrincipal.getUserId(), userUpdateDto.toEntity());
//		userPrincipal.setUser(userEntity);
		
		Principal newPrincipal = new Principal(userEntity);
	    userPrincipal.setPrincipal(newPrincipal);
		
		
		return new CMRespDto<>(1, "회원수정완료", userEntity); // 응답시에 userEntity의 모든 getter 함수 호출, JSON으로 파싱하여 응답
	}
	
//	@PutMapping("/api/user/profileImageUrl")
//	public ResponseEntity<?> profileImageUrlUpdate(@AuthenticationPrincipal UserPrincipal userPrincipal, MultipartFile profileImageFile){
//		User userEntity = userService.userProfileImageModify(userPrincipal, profileImageFile);
////		userPrincipal.setPrincipal(userEntity);
//		
//		return new ResponseEntity<>(new CMRespDto<>(1, "프로필사진변경 성공", null), HttpStatus.OK);
//	}
	
}

