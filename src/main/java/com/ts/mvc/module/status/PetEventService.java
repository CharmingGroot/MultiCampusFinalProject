package com.ts.mvc.module.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.status.dto.PetEventDto;
import com.ts.mvc.module.user.UserRepository;

@Service
@Transactional(readOnly = true)
public interface PetEventService {
	
	List<PetEventDto> findAllPetEvent();
	
	PetEventDto savePetEvent(PetEventDto petEventDto);

	 // 삭제하기 
       void delete(Long eventId);
	
	
//	private final UserRepository userRepository;
//	private final PetEventRepository PetEventRepository;
	
	// 의존성 주입 
//	  public PetEventService(PetEventRepository petEventRepository, UserRepository userRepository) {
//	        this.PetEventRepository = petEventRepository;
//			this.userRepository = userRepository;
//	    }
	  
	  
//		@Autowired
//		private PetEventRepository petEventRepository;
//		
//		public PetEvent save(PetEventDto petEventDto) {
//			
//			PetEvent petEvent = new PetEvent(); 
//			
//			petEvent.setTitle(petEventDto.getTitle());
//			petEvent.setStart(petEventDto.getStart());
//		    petEvent.setEnd(petEventDto.getEnd());
//		    petEvent.setEventIdx(petEventDto.getEventIdx());
//		    petEvent.setPet(petEventDto.getPet());
//		    petEvent.setUser(petEventDto.getUser());
//		    
//		    return petEventRepository.save(petEvent);
//		}
//		

//	  
//		// 모든 일정 가져오기
//		public List<PetEvent> getAll(){
//			return petEventRepository.findAll();
//			}
		
//	  @Transactional
//	  public PetStatusSchedule createSchedule(PetStatusScheduleDto dto) {
//		  
//		  User user = userRepository.findById(dto.getUserId()).get(); //userId를 찾고,
//		  PetStatusSchedule schedule = PetStatusSchedule.createPetStatusSchedule(dto, user);
//		  
//		  return petStatusRepository.saveAndFlush(schedule);
//	  }
	  
//	  public void deleteSchedule(PetStatusScheduleDto dto) {
//	       petStatusRepository.deleteBySchedule(dto);
//	  }
//	  
	 
	  // 풀캘린더 수정이 안되서 일단 보류
//	  public void updateSchedule(String userId,PetStatusScheduleDto dto) {
//		  User existingUser = userRepository.findByUserId(userId);
//		  
//	  }
	  
//	//public String uploadSchedule(PetStatusScheduleDto ScheduleDto){
//	public List<Map<String, Object>> getEventList(){
//		Map<String, Object> event = new HashMap<String, Object>();
//		List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
//		
//		event.put("title", "test");
//		event.put("allday", "test");
//		event.put("Start", LocalDate.now());
//		event.put("End", LocalDate.now());
//		eventList.add(event);
//		event = new HashMap<String,Object>();
//		event.put("title","test2");
//		event.put("start", LocalDate.now());
//		event.put("End", LocalDate.now());
//		eventList.add(event);
//		
//		return eventList;
//	}
//	
//	  public PetEvent createPetStatusSchedule(Long eventIdx, String title, LocalDateTime start, LocalDateTime end, Pet pet, User user) {
//		
//		  PetEvent petEvent = new PetEvent();
//		  
//		  petEvent.setTitle(title);
//		  petEvent.setStart(start);
//		  petEvent.setEnd(end);
//		  petEvent.setEventIdx(eventIdx);
//		  petEvent.setPet(pet);
//		  petEvent.setUser(user);
//		  
//		  
//		  return PetEventRepository.save(petEvent);
//		  
//	  }
	
}
