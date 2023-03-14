package com.ts.mvc.module.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Blog {

	private String water;
	private String food;
	private String weight;
	private double totalDistance;
	
	public Blog(String water, String food, String weight, double totalDistance) {
		this.water = water;
		this.food = food;
		this.weight = weight;
		this.totalDistance = totalDistance;
	}
	
	
	



	@Override
	public String toString() {
		return "{ water :" + water + "\" + "
				+ "food :" + food + "\" +"
						+ "weight :" + weight + "\" +"
								+ "totalDistance :" +  totalDistance + "}";
	}
	
}
