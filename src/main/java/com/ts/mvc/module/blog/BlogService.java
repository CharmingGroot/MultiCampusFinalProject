package com.ts.mvc.module.blog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.infra.exception.CustomException;
import com.ts.mvc.module.blog.dto.request.FoodDto;
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
	public void createWalkStatus(WalkDto walkDto) {
		System.out.println("BlogService 서비스레이어의 createWalkStatus 실행.");

		User user = userRepository.findById(walkDto.getUserId()).get();
//		Pet pet = petRepository.findByPetName(walkDto.getPetName()).get();
		
		PetStatus petStatus = PetStatus.createWalkStatus(user,walkDto);
				
		petStatusRepository.saveAndFlush(petStatus);
		
	}
	
	@Transactional
	public void updateWalkStatus(WalkDto walkDto, PetStatus filteredList) {
		System.out.println("BlogService 서비스레이어의 updateWalkStatus 실행.");
		System.out.println("walkDto 는 : "+ walkDto);
		System.out.println("filteredList 는 : "+ filteredList);
		
		
		User user = userRepository.findById(walkDto.getUserId()).get();
		
//		Optional<PetStatus>selectedByIdxPetStatus = petStatusRepository.findById(filteredList.getStatusIdx()); // 해당 pk값으로 조회
//		PetStatus petStatus = selectedByIdxPetStatus.get();
		
		
		PetStatus petStatus = filteredList;
		
		petStatus.setWalkDistance(petStatus.getWalkDistance()+walkDto.getWalkDistance());
		petStatus.setWalkTime(petStatus.getWalkTime()+walkDto.getWalkTime());
		
		
		
		
		petStatusRepository.saveAndFlush(petStatus);
		
	}


	@Transactional
	public void createFeedStatus(FoodDto dto) {
		
		System.out.println("BlogService 서비스레이어의 createFeedStatus 실행.");

		User user = userRepository.findById(dto.getUserId()).get();
//		Pet pet = petRepository.findByPetName(walkDto.getPetName()).get();
		
		PetStatus petStatus = PetStatus.createFeedStatus(user, dto);
				
		petStatusRepository.saveAndFlush(petStatus);

	}
	
	@Transactional
	public void updateFeedStatus(PetStatus petStatus ,FoodDto dto) {
		
		System.out.println("BlogService 서비스레이어의 updateFeedStatus 실행.");
		User user = userRepository.findById(dto.getUserId()).get();
		
		petStatus.setRegDate(LocalDateTime.now());
		petStatus.setFood(petStatus.getFood()+dto.getFood());
		petStatus.setWater(petStatus.getWater() +dto.getWater());
		petStatus.setWeight(dto.getWeight());
		
		petStatusRepository.saveAndFlush(petStatus);
		
	}


	

	
	
	
}
