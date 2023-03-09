package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder @NoArgsConstructor @AllArgsConstructor @Getter
public class Status {

	@Id
	private String chosenDate;
	
	private Integer food;
	private Integer water;
	private Integer walkTime;
	private Integer walkDistance;
	private Integer treat;
	private Double weight;
	
	private String todoList;
	private String todoDate;
	
	@ManyToOne
	@JoinColumn(name = "email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "petIdx")
	private Pet pet;
}
