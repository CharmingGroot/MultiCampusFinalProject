package com.ts.mvc.module.status;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ts.mvc.module.status.dto.PetEventDto;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PetEventService {
	  
		@Autowired
		private PetEventRepository petEventRepository;
		
		public void addPetEvent(PetEventDto petEventDto) {
			PetEvent petEvent = new PetEvent(petEventDto.getEventIdx(), petEventDto.getTitle(),petEventDto.getStart()
					                        ,petEventDto.getEnd(),  petEventDto.getUser(), petEventDto.getPet());
			
			petEventRepository.save(petEvent);
		}

	 // 삭제하기 
       public void deletePetEvent(Long eventId) {
    	   petEventRepository.deleteById(eventId);
       }
    
       public List<PetEventDto> getAllPetEvents() {
             List<PetEvent> petEvents = petEventRepository.findAll();
             return petEvents.stream().map(this::toPetEventDto).collect(Collectors.toList());
         }
       
       // 전체 조회
       private PetEventDto toPetEventDto(PetEvent petEvent) {
    	    PetEventDto petEventDto = new PetEventDto();
    	    
    	    petEventDto.setEventIdx(petEvent.getEventIdx());
    	    petEventDto.setTitle(petEvent.getTitle());
    	    petEventDto.setStart(petEvent.getStart());
    	    petEventDto.setEnd(petEvent.getEnd());

    	    return petEventDto;
    	}

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
