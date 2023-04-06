package com.ts.mvc.module.blog.dto.request;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkDto {
	
	private int walkTime;
	private double walkDistance;
	private LocalDateTime regDate;
	private String user;
	private String pet;


}
