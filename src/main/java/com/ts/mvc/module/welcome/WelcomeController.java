package com.ts.mvc.module.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ts.mvc.infra.api.OpenAI;
import com.ts.mvc.module.welcome.dto.WelcomePetQuestionRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WelcomeController {
	
	private final WelcomeService welcomeService;
	
	@GetMapping("")
	public String welcome() {
		return "/html/index";
	}
	
	
//	@PostMapping("aiQuestion")
//	public String aiAnswer() {
//		welcomeService.createQuestionResult();
//		return "/html/index";
//	}
	
}
