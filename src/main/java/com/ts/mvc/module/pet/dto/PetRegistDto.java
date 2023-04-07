package com.ts.mvc.module.pet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetRegistDto {
	
	private String userId;
	
	private String petName;
	
	private String petBirthdate;
	
	private String petNumber;
	
	private Double weight;
	
	private String breed;
	
	private String gender;
	
	private String neutering;
	
	private String dogMbti;
}
