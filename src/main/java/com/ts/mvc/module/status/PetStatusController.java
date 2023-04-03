package com.ts.mvc.module.status;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ts.mvc.module.status.dto.PetStatusDto;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("status")
@AllArgsConstructor
public class PetStatusController {

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		petStatus.setWater(request.getParameter("water"));
		petStatus.setFood(request.getParameter("food"));
		petStatus.setWeight(request.getParameter("weight"));
		petStatus.setTreat(request.getParameter("treat"));
		petStatus.setWalkDistance(request.getParameter("walkDistance"));
		  
		return "/html/status";
	}
	
//	   @GetMapping("sb")
//	   public String sb() {
//	      return "/html/calendar";
//	   }


}
