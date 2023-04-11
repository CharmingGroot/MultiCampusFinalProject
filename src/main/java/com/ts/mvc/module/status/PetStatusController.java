package com.ts.mvc.module.status;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.mvc.module.status.dto.PetEventDto;
import com.ts.mvc.module.status.dto.PetStatusDto;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("status")
@AllArgsConstructor
public class PetStatusController {

	private PetEventService petEventService;
	private PetEventRepository petEventRepository;

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		petStatus.setWater(request.getParameter("water"));
		petStatus.setFood(request.getParameter("food"));
		petStatus.setWeight(request.getParameter("weight"));
		petStatus.setTreat(request.getParameter("treat"));
		petStatus.setWalkDistance(request.getParameter("walkDistance"));
		  
		return "/html/status";
	}
	  
    //전체 이벤트 조회
//	@GetMapping("allEvents")
//	@ResponseBody // 객체면 전체 전송가능 
//	public String getAllPetEvents(){
//		System.out.println("확인용22");
//     	List<PetEventDto> petEvents = petEventService.getAllPetEvents();
//     	System.out.println("확인용");
//     	
//		return  "/status";
//		
//	}
	
	// 일정 등록
	@PostMapping("eventUpload")
	@ResponseBody 
	public ResponseEntity<Void> addPetEvent(@RequestBody String content, PetEventDto petEventDto) { // content에 petEventDto를 담는다. 
		System.out.println(content);
		
		petEventService.addPetEvent(petEventDto);
     	System.out.println("확인용");
     	
		return ResponseEntity.ok().build();
	}
	// 일정 삭제
	@ResponseBody 
	@DeleteMapping("eventDelete/{eventIdx}")
	public String deletePetEvent(@PathVariable Long eventIdx){
		
		petEventService.deletePetEvent(eventIdx);
		return "/status/eventDelete";		
	}
	
	
}


