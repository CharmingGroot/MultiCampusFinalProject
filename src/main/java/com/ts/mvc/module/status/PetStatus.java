package com.ts.mvc.module.status;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
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
public class PetStatus {

	@Id
	@GeneratedValue
	private Long statusIdx;
	
	@Column(columnDefinition = "timestamp default now()")
	private String chosenDate;
	
	@ColumnDefault("0")
	private Integer food;
	
	@ColumnDefault("0")
	private Integer water;
	
	@ColumnDefault("0")
	private Integer walkTime;
	
	@ColumnDefault("0")
	private Double walkDistance;
	
	@ColumnDefault("0")
	private Integer treat;

	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Pet pet;
	
//	@ApiModelProperty(value = "해당 년월일", example = "2022-04-19", required = true)
//  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//  private LocalDate date;
	
	
}
