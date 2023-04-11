package com.ts.mvc.module.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ts.mvc.module.diary.Diary;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
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

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"user"}) // JPA 무한참조 방지
    private List<Pet> pets;
	
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	@Builder.Default
//	private List<Badge> badges = new ArrayList<>();
	
	// User를 Select할 때 해당 User Id로 등록된 image들을 다 가져온다.
	// Lazy = User를 Select할 때 해당 User Id로 등록된 image들을 안가져옴, 대신 getImages() 함수의 image들이 호출될 때 가져옴
	// Eager = User를 Select할 때 해당 User Id로 등록된 image들을 전부 Join해서 가져옴 
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"}) // JPA 무한참조 방지
	private List<Diary> images;

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

	public void modifyUser(String password, String nickname) {
		this.password = password;
		this.nickname = nickname;
		
	}
}
