package com.ts.mvc.module.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlogDto {

	private String water;
	private String food;
	private String weight;
	private String totalDistance;
	
	public BlogDto(String water, String food, String weight, String totalDistance) {
		this.water = water;
		this.food = food;
		this.weight = weight;
		this.totalDistance = totalDistance;
	}

	
	
}
