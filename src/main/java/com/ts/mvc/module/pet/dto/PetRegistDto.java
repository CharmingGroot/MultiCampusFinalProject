package com.ts.mvc.module.pet.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetRegistDto {

	@NotBlank
	private String petName;
	
	@NotBlank
	private String petBirthdate; // 생일
	
	@NotBlank
	private String breed; // 종
	
	private String petNumber; // 동물고유번호
	
	@NotBlank
	private Double weight;

	@NotBlank
	private Boolean gender;
	
	@NotBlank
	private Boolean isNeutered;

	@NotBlank
	private String dogMbti;

	private String user;
}
