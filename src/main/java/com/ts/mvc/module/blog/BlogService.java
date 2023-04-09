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
		
		PetStatus petStatus = PetStatus.createData(user,walkDto);
				
		petStatusRepository.saveAndFlush(petStatus);
		
	}
	
	@Transactional
	public void updateWalkStatus(WalkDto walkDto) {
		System.out.println("BlogService 서비스레이어의 updateWalkStatus 실행.");

		// regDate와 petName과 userId가 일치해야함.
		// regDate는 현재날짜와 비교하는 로직 활용.
		
		// Optional<PetStatus> petStatus에 조건에 맞는 필드를 조회한 뒤
		// PetStatus petStatus = petStatus.get();
		// Save n flush 해주면 끗.
		
		User user = userRepository.findById(walkDto.getUserId()).get();
//		Pet pet = petRepository.findByPetName(walkDto.getPetName()).get();
		
		PetStatus petStatus = PetStatus.createData(user,walkDto);
				
		petStatusRepository.saveAndFlush(petStatus);
		
	}

	@Transactional
	public void updateFoodStatus(FoodDto dto) {
		System.out.println("updateFoodStatus 실행");
		
		List<PetStatus> petStatus = petStatusRepository.findByPetNameAndUserUserId(dto.getPetName(),dto.getUserId())
				.stream().filter(entity -> entity != null).collect(Collectors.toList());
		
//		System.out.println("petStatus 는 : "+petStatus);
		
	
//		for(PetStatus status : petStatus) {
//			System.out.println(status.getRegDate());
//		}

		 // UserId를 updateFoodData에 넘겨주기 위해 불러옴.
		User user = userRepository.findById(dto.getUserId()).get();
				
		PetStatus updatedPetStatus = PetStatus.updateFoodData(user, dto);
		
		
		
		// 입력받은 dto값들과 엔티티의 값을 비교하기 위해 엔티티를 조회. (userId, petName)
		// 만약 petStatus의 regDate가 현재시간의 YY:MM:DD까지 일치한다면,
		// petStatus의 데이터를 교체(값 증감)하고 save
		List<PetStatus> entityValidation = petStatusRepository.findByPetNameAndUserUserId(dto.getPetName(),dto.getUserId());
		
		
		
		// entityValidation를 순회하면서 regDate값 일치여부 판단
		for(PetStatus status : entityValidation) {
			
			LocalDateTime dateTime = status.getRegDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy:MM:dd");
			String formattedDateTime = dateTime.format(formatter);
			
			LocalDateTime nowTime = LocalDateTime.now();
			String formattedNowTime = nowTime.format(formatter);
			
			
			// 포매팅된 날짜데이터와 현재 날짜가 같다면 petStatus데이터를 교체
			if(formattedDateTime.equals(formattedNowTime)) {
				
				System.out.println("LocalDateTime이 같습니다.");
				// dto의 값으로 petStatus의 값을 덮어쓴다.
				
			} 
			
		}
		
		
		petStatusRepository.saveAndFlush(updatedPetStatus);

		
	}


	

	
	
	
}
