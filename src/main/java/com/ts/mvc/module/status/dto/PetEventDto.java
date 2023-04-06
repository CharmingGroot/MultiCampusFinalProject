package com.ts.mvc.module.status.dto;

import java.time.LocalDateTime;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetEventDto {
	
	// 어떤 값을 사용할지
	private Long eventIdx; // pk값
	private String title;
	private LocalDateTime start;
	private LocalDateTime end;
	private Pet pet;
    private User user;
	
	public PetEventDto(Long eventIdx, String title, LocalDateTime start, LocalDateTime end, Pet pet, User user) {
		this.eventIdx = eventIdx;
		this.title = title;
		this.start = start;
		this.end = end;
		this.pet = pet;
		this.user= user;
		
	}

	@Override
	public String toString() {
		return "PetEventDto [eventIdx=" + eventIdx + ", title=" + title + ", start=" + start + ", end="
				+ end + ", pet=" + pet + ", user=" + user + "]";
	}
	
	
	

}
