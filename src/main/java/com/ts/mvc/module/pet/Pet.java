package com.ts.mvc.module.pet;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.infra.util.file.profile.ProfileFilePath;
import com.ts.mvc.module.guestbook.GuestBook;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Pet {

	@Id
	@GeneratedValue
	private Long petIdx;
	
//	private ProfileFilePath petImg;
	
	private String petName;
	private String petBirthdate;
	private String breed;
	private String petNumber;
	private String petCharacter;
	
	private Boolean gender;
	private Boolean isNeutered;

	@ManyToOne
	@JoinColumn(name = "email")
	private User user;
}
