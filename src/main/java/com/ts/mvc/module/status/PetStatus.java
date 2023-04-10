package com.ts.mvc.module.status;

import java.time.LocalDate;
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

import com.ts.mvc.module.blog.dto.request.FoodDto;
import com.ts.mvc.module.blog.dto.request.WalkDto;
import com.ts.mvc.module.pet.Pet;
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
	private Double weight;
	
	@ColumnDefault("0")
	private Integer treat;

	@Column(columnDefinition = "timestamp default now()")
	private LocalDateTime regDate;
	
//	@Column(columnDefinition = "DATE NOT NULL DEFAULT CURRENT_DATE")
//	private LocalDate regDate;
	
	@ManyToOne
	private User user;
	
	// petName은 pet엔티티의 ID가 petIdx로 설정되어있기때문에, 굳이 연관관계 매핑을 하지않았습니다. 대신 빌딩할때 Pet엔티티에서 값을 받아오는 형태로 사용하려고 String으로 설정했습니다.
	private String petName; 

	
	public static PetStatus createData(User user,WalkDto walkDto) {
		return PetStatus.builder()
				.user(user)
				.petName(walkDto.getPetName())
				.walkDistance(walkDto.getWalkDistance())
				.walkTime(walkDto.getWalkTime())
				.build();
	}


	public static PetStatus updateFoodData(User user,FoodDto dto) {
		return PetStatus.builder()
				.food(dto.getFood())
				.water(dto.getWater())
				.petName(dto.getPetName())
				.weight(dto.getWeight())
				.user(user)
				.build();
	}


	public static PetStatus createWalkStatus(User user, WalkDto walkDto) {
		return PetStatus.builder()
				.user(user)
				.petName(walkDto.getPetName())
				.walkDistance(walkDto.getWalkDistance())
				.walkTime(walkDto.getWalkTime())
				.build();
	}


	public static PetStatus createFeedStatus(User user2, FoodDto dto) {
		return PetStatus.builder()
				.user(user2)
				.petName(dto.getPetName())
				.water(dto.getWater())
				.food(dto.getFood())
				.weight(dto.getWeight())
				.build();
	}

	
	
}
