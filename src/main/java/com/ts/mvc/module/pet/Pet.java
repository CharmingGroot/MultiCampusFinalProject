package com.ts.mvc.module.pet;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.ts.mvc.module.guestbook.GuestBook;
import com.ts.mvc.module.pet.dto.PetRegistDto;
import com.ts.mvc.module.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert // insert 쿼리를 생성할 때 null인 필드는 쿼리에서 생략
@DynamicUpdate // entity에서 변경이 발견되지 않은 값은 쿼리에서 생략
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter 
public class Pet {

	@Id
	@GeneratedValue
	private Long petIdx;

	private String petName;
	private String petBirthdate; // 생일
	private String breed; // 종
	private String petNumber; // 동물고유번호
	
	private Double weight;

	private String gender;
	private String neutering;

	private String dogMbti;

	@ManyToOne
	private User user;
	
	public static Pet registPet(PetRegistDto dto, User user) {
		return Pet.builder()
				.petName(dto.getPetName())
				.petBirthdate(dto.getPetBirthdate())
				.breed(dto.getBreed())
				.weight(dto.getWeight())
				.petNumber(dto.getPetNumber())
				.gender(dto.getGender())
				.neutering(dto.getNeutering())
				.dogMbti(dto.getDogMbti())
				.user(user)
				.build();
	}

}
