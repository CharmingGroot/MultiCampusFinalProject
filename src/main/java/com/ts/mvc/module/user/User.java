package com.ts.mvc.module.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.infra.util.badge.Badge;
import com.ts.mvc.module.user.dto.request.SignUpRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class User {
	
	@Id
	private String email;

	private String userName;
	private String password;	
	private String tell;
	
	@ColumnDefault("false")
	private Boolean isLeave;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	private String profilePhoto;
	
	private String address;
	
	@OneToMany
	@Builder.Default
	private List<Badge> badges = new ArrayList<>();
	
	public static User createUser(SignUpRequest dto) {
		return User.builder()
				   .email(dto.getEmail())
				   .userName(dto.getUserName())
				   .password(dto.getPassword())
				   .tell(dto.getTell())
				   .build();
	}
}
