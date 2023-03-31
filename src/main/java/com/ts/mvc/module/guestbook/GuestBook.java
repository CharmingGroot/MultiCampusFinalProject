package com.ts.mvc.module.guestbook;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class GuestBook {
	
	@Id
	@GeneratedValue
	private Long gbIdx;
	
	private String content;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ManyToOne
	private User user; // 수신자 = 방명록 주인
	
	@ManyToOne
	private User nickname; // 주인 닉네임 Controller로 값을 받으면 해당 값을 할당할 수 있도록하자.
	
	
	public static GuestBook createGuestBook(GuestBookRegistRequest dto, User user) {
		
//		System.out.println("GuestBook의 CreateBook메서드 실행");
//		System.out.println("dto.nickname" + dto.getNickname());
//		System.out.println("User.nickname" + user.getNickname());
		
		return GuestBook.builder()
				.user(user)
				.nickname(user)
				.content(dto.getContent())
				.build();
	}
}
