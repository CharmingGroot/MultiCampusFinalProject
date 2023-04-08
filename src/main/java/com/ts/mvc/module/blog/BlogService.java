package com.ts.mvc.module.blog;

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
	public void createWalkStatus(WalkDto walkDto, FoodDto foodDto) {
		System.out.println("BlogService 서비스레이어의 createStatus 실행.");

		User user = userRepository.findById(walkDto.getUserId()).get();
		Pet pet = petRepository.findByPetName(walkDto.getPetName()).get();
		
		// 업로드할 petStatus에 regDate의 등록일이 같은 데이터가 있다면 가장 최근 데이터에 값을 더하여 추가.해야함
		
		PetStatus petStatus = PetStatus.updateData(user,pet,walkDto,foodDto);
				
		petStatusRepository.saveAndFlush(petStatus);
		
	}

	@Transactional
	public void updateFoodStatus(FoodDto dto) {
		System.out.println("updateFoodStatus 실행");
		
		
		
		List<PetStatus> petStatus = petStatusRepository.findByPetNameAndUserUserId(dto.getPetName(),dto.getUserId())
				.stream().filter(entity -> entity != null).collect(Collectors.toList());
		
		System.out.println("petStatus 는 : "+petStatus);
		
		// regDate가 같은 값 && 가장 큰 idx를 가진 값에 dto의 필드값을 더하고 엔티티를 업데이트.
//		for(int i=0; i< petStatus.size();i++) {
//			System.out.println(i);
//			System.out.println(petStatus.get(i));
//		}
		
		for(PetStatus status : petStatus) {
			System.out.println(status.getRegDate());
		}
		
		User user = userRepository.findById(dto.getUserId()).get();

		
		// userId와 petName이 일치하며 오늘날짜인 데이터를 가져와야함.
		
		PetStatus updatedPetStatus = PetStatus.updateFoodData(user, dto);
		
		System.out.println("updatePetStatus는 : "+ updatedPetStatus);
		System.out.println(updatedPetStatus.getFood());	
		System.out.println(updatedPetStatus.getWater());		

		
		petStatusRepository.saveAndFlush(updatedPetStatus);

		
	}


	

	
	
	
}
