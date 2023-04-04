package com.ts.mvc.module.guestbook;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visitor")
	private User user; // 방문자
	
	private String pageOwner; // 방명록 오너
	
	
	public static GuestBook createGuestBook(GuestBookRegistRequest dto, User user) {
		
		return GuestBook.builder()
				.user(user) // 방문자
				.pageOwner(dto.getPageOwner()) // 페이지 주인
				.content(dto.getContent())
				.build();
	}
}
