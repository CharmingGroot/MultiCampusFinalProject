package com.ts.mvc.infra.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetQuestionResponse {
	
	private String message;
	private String role;
	private Long created;

}
