package com.ts.mvc.module.pet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pet")
public class PetController {

	@GetMapping("regist")
	public String petRegist() {
		return "/html/pet-register";
	}
	
	@GetMapping("modify")
	public String petModify() {
		return "/html/pet-modify";
	}
	

}
