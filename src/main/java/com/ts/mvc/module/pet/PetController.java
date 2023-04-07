package com.ts.mvc.module.pet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ts.mvc.module.pet.dto.PetRegistDto;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("pet")
@AllArgsConstructor
public class PetController {
	
	private PetService petService;

	@GetMapping("regist")
	public String petRegist() {
		return "/html/pet-register";
	}
	
	@PostMapping("register")
	public String petRegister(PetRegistDto dto, @RequestParam("breed") String selectedBreed) {
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		dto.setBreed(selectedBreed);
		petService.registPet(dto);
		
		return "redirect:/";
	}
	
	@GetMapping("modify")
	public String petModify() {
		return "/html/pet-modify";
	}
	

}
