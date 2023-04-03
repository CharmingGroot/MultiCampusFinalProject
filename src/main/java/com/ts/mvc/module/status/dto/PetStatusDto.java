package com.ts.mvc.module.status.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetStatusDto {
	
	private String food;
	private String water;
	private String walkDistance;
	private String treat;
	private String weight;
	
	public PetStatusDto(String food, String water, String walkDistance, String treat, String weight) {
		this.food = food;
		this.water = water;
		this.walkDistance = walkDistance;
		this.treat = treat;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return " [food=" + food + "\"+ "
				+ "water=" + water + "\" + "
				+ "walkDistance=" + walkDistance + "\" + "
			    + "treat=" + treat + "\" + "
				+ "weight=" + weight + "]";
	}
	
	
	
	
	
	
	
}
