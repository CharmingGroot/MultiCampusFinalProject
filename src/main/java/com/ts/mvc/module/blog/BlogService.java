package com.ts.mvc.module.blog;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.blog.dto.request.WalkDto;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.pet.PetRepository;
import com.ts.mvc.module.status.PetStatus;
import com.ts.mvc.module.status.PetStatusRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BlogService {
	
	private final PetStatusRepository petStatusRepository;
	private final UserRepository userRepository;
	private final PetRepository petRepository;
	
	@Transactional
	public void createStatus(WalkDto dto) {
		System.out.println("서비스레이어의 createStatus 실행.");
		System.out.println("dto는 : "+ dto);

		User user = userRepository.findById(dto.getUserId()).get();
		Pet pet = petStatusRepository.findByPetName(dto.getPetName()).get();
		System.out.println("user는 : "+user);
		PetStatus petStatus = PetStatus.updateWalkData(user,pet,dto);
		
//		PetStatus petStatus = PetStatus.updateWalkData(dto);
		
		petStatusRepository.saveAndFlush(petStatus);
		
	}


	

	
	
	
}
