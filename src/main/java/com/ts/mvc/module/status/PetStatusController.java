package com.ts.mvc.module.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.mvc.module.status.dto.PetStatusDto;

@Controller
@RequestMapping("status")
public class PetStatusController {
	
//	@Autowired
//	private PetEventRepository petEventRepository;
//	private PetEventService petEventService;


	// 생성자를 만들어서 의존성 주입
//	public PetStatusController(PetEventService petEventService) {
//		this.petEventService = petEventService;
//	}

	@GetMapping("")
	public String statusUpdate(HttpServletRequest request, Model model, PetStatusDto petStatus) {
		petStatus.setWater(request.getParameter("water"));
		petStatus.setFood(request.getParameter("food"));
		petStatus.setWeight(request.getParameter("weight"));
		petStatus.setTreat(request.getParameter("treat"));
		petStatus.setWalkDistance(request.getParameter("walkDistance"));
		  
		return "/html/status";
	}
	
	// 일정 등록
//	@PostMapping("")
//	@ResponseBody
//	public ResponseEntity<Void> savePetEvent(@RequestBody PetEventDto petEventDto) {
//		
//		petEventService.save(petEventDto);
//		return ResponseEntity.status(HttpStatus.CREATED).build();
//	}
//	
//	public PetEvent createEvent(@RequestBody PetEventDto petEventDto) {
//		return petEventRepository.saveAll(petEventDto);
//	}
	
	// 일정 삭제

//	@DeleteMapping("delete")
//	@ResponseBody
//	public ResponseEntity<Void> deletePetEvent(@PathVariable Long eventId){
//		
//		petEventService.delete(eventId);
//		return ResponseEntity.ok().build(); // 전송 성공시 200반환 
//		
//	}
	
	// 전체 이벤트 조회
	
//	@GetMapping("")
//	public ResponseEntity<List<PetEvent>> getPetEvents(){
//		List<PetEvent> petEvents = petEventService.getAll();
//		return ResponseEntity.ok(petEvents);
//	}
	
//	public void delete(@PathVariable Long eventIdx) {
//		PetEvent petEvent = petEventRepository.findById(eventIdx)
//				             .orElse(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
//		
//		petEventRepository.delete(petEvent); // 일정 삭제
//	}
	 
//	@PostMapping("")
//	public PetStatusSchedule createPetStatusSchedule(@RequestBody PetStatusScheduleDto petStatusScheduleDto) {
//		
//		String title = petStatusScheduleDto.getTitle();
//		LocalDateTime start = petStatusScheduleDto.getStart();
//		LocalDateTime end = petStatusScheduleDto.getEnd();
//		Long scheduleIdx = petStatusScheduleDto.getScheduleIdx();
//		Pet pet = petStatusScheduleDto.getPet();
//		User user = petStatusScheduleDto.getUser();
//		
//		// 받아온 데이터를 이용하여 일정 생성
//		PetStatusSchedule petStatusSchedule = PetStatusScheduleService.createPetStatusSchedule(scheduleIdx,title,start,end,pet,user);
//		                                                     
//		return petStatusSchedule;
//		
//     	}

//	@PostMapping("")
//	@ResponseBody
//	public String addEvents(@RequestBody List<Map<String, Object>> param) throws Exception {
//
//		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.KOREA);
//		
//	     for(int i =0; i<param.size(); i++) {
//	     
//	    	 String title =(String) param.get(i).get("title");
//	         String start= (String) param.get(i).get("start");
//	         String end = (String) param.get(i).get("end");
//              
//	     }
//	
//		 return "/html/status";
//}
//	@PostMapping("")
//   
//    public  @ResponseBody List<Map<String,Object>> getEvent(){
//		return statusService.getEventList();
//	}
	
	
}


