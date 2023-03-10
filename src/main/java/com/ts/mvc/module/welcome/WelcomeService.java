package com.ts.mvc.module.welcome;

import org.springframework.stereotype.Service;

import com.ts.mvc.infra.api.OpenAI;
import com.ts.mvc.module.welcome.dto.WelcomePetQuestionRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WelcomeService {
	
	private final OpenAI openAI;

//	public void createQuestionResult(WelcomePetQuestionRequest wpqr) {
//		openAI.petQuestion(wpqr);
//		
//	}

}
