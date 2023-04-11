package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.status.dto.PetEventDto;
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
@Table(name="pet_event") // db에 생기는 테이블 이름 지정
public class PetEvent {

	@Id // pk임을 지정하는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventIdx;
	
	private String title; // 일정이름 
	private LocalDateTime start; // 시작날짜
	private LocalDateTime end; // 종료날짜
	
	@ManyToOne //PetEvent :n user :1
    private User user;
	
	@ManyToOne //PetEvent :n pet:1
    private Pet pet;
	
	

}
