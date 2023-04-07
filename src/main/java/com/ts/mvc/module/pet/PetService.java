package com.ts.mvc.module.pet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.module.pet.dto.PetRegistDto;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PetService {
	
	private final UserRepository userRepository;
	private final PetRepository petRepository;
	
	@Transactional
	public void registPet(PetRegistDto dto) {
		User user = userRepository.findById(dto.getUserId()).get();
		Pet pet = Pet.registPet(dto, user);
		
		petRepository.saveAndFlush(pet);
	}

}
