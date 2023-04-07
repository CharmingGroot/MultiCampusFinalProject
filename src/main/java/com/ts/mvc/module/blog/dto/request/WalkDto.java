package com.ts.mvc.module.blog.dto.request;

import java.time.LocalDateTime;

import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WalkDto {
	
	private int walkTime;
	private double walkDistance;
	private LocalDateTime regDate;
	private String userId;
	private String petName;


}
