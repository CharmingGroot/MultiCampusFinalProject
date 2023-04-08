package com.ts.mvc.module.blog.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FoodDto {

	private int water;
	private int food;
	private double weight;
	private String petName;
	private String userId;
	
	public FoodDto(int water, int food, double weight, String petName, String userId) {
		this.water = water;
		this.food = food;
		this.weight = weight;
		this.petName = petName;
	}

	
	
}
