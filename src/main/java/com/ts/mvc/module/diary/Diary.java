package com.ts.mvc.module.diary;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.infra.util.file.diary.DiaryFilePath;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Diary {
	
	@Id
	@GeneratedValue
	private Long bdIdx;
	
	@ManyToOne
	@JoinColumn(name = "email")
	private User user;
	
	private String title;
	private String content;
	
//	@OneToOne
//	@Builder.Default
//	private DiaryFilePath file;
	
	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
}
