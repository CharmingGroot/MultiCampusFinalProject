package com.ts.mvc.module.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.infra.util.badge.Badge;
import com.ts.mvc.module.guestbook.GuestBook;
import com.ts.mvc.module.user.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class User {
	
	@Id
	private String userId;

	private String password;
	private String email;
	private String nickname;
	private String profileImageUrl;
	private String grade;

	
	
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@OneToMany
	@Builder.Default
	private List<Badge> badges = new ArrayList<>();
	
	
	
	
	
	public static User createUser(SignUpRequest dto) {
		return User.builder()
				   .userId(dto.getUserId())
				   .password(dto.getPassword())
				   .email(dto.getEmail())
				   .nickname(dto.getNickname())
				   .profileImageUrl(dto.getProfileImageUrl())
				   .grade(dto.getGrade())
				   .build();
	}








	
}
