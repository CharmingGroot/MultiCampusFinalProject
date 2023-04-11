package com.ts.mvc.module.status.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

import lombok.Data;

@Data
public class PetEventDto {
	
	// 어떤 값을 사용할지
	private Long eventIdx; // pk값
	private String title;
	private LocalDateTime start;
	private LocalDateTime end;
	private User user;
	private Pet pet;
	
	public PetEventDto() {
		
	}
	
	public PetEventDto(Map<String, Object> jsonMap) {
	    this.eventIdx = (Long) jsonMap.getOrDefault("eventIdx", 0L);
	    this.title = (String) jsonMap.getOrDefault("title", "");
	    this.start = LocalDateTime.parse((String) jsonMap.getOrDefault("start", ""), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	    this.end = LocalDateTime.parse((String) jsonMap.getOrDefault("end", ""), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	    this.user = (User) jsonMap.getOrDefault("user", new User());
	    this.pet = (Pet) jsonMap.getOrDefault("pet", new Pet());
	}

    
//	public PetEventDto(Map<String, Object> jsonMap) throws JsonProcessingException {
//	    ObjectMapper mapper = new ObjectMapper();
//	    String jsonString = mapper.writeValueAsString(jsonMap); // writeValueAsString으로 jsonMap을 json으로 문자열 변환
//	    
//	    PetEventDto dto = mapper.readValue(jsonString, PetEventDto.class); //readValue 얘를 이용해서 PetEventDto 객체로 변환 
//	    
//	    this.eventIdx = dto.getEventIdx();
//	    this.title = dto.getTitle();
//	    this.start = dto.getStart();
//	    this.end = dto.getEnd();
//	    this.user = dto.getUser();
//	    this.pet = dto.getPet();
//	}

	
	public PetEventDto(Long eventIdx, String title, LocalDateTime start, LocalDateTime end, User user, Pet pet) {
		this.eventIdx = eventIdx;
		this.title = title;
		this.start = start;
		this.end = end;
		this.user= user;
		this.pet = pet;
		
		
	}

	@Override
	public String toString() {
		return "PetEventDto [eventIdx=" + eventIdx + ", title=" + title + ", start=" + start + ", end="
				+ end + ", pet=" + pet + ", user=" + user + "]";
	}
	
	
	

}
