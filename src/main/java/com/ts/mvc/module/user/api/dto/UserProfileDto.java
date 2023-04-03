package com.ts.mvc.module.user.api.dto;

import com.ts.mvc.module.user.User;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

	private boolean pageOwnerState;
	private int imageCount;
	private User user;
}
