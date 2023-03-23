package com.ts.mvc.module.status;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ts.mvc.module.blog.dto.BlogDto;
import com.ts.mvc.module.guestbook.GuestBookService;
import com.ts.mvc.module.status.dto.PetStatusDto;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("pet")
@AllArgsConstructor
public class PetStatusController {

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		String water = request.getParameter("water");
		String food = request.getParameter("food");
		String weight = request.getParameter("weight");
		String treat = request.getParameter("treat");
		String walkDistance = request.getParameter("walkDistance");
		  
     if(water != null || food != null || weight != null ) {   
		         model.addAttribute("water", water);
		         System.out.println(petStatus.toString());
		      }
		return "/html/status";
	}
	   @GetMapping("sb")
	   public String sb() {
	      return "/html/teststatus";
	   }


}
