package com.ts.mvc.module.blog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.mvc.module.blog.dto.request.FoodDto;
import com.ts.mvc.module.blog.dto.request.WalkDto;
import com.ts.mvc.module.guestbook.GuestBookService;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.pet.PetRepository;
import com.ts.mvc.module.status.PetStatus;
import com.ts.mvc.module.status.PetStatusRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.dto.Principal;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("blog")
@AllArgsConstructor
public class BlogController {

	private final BlogService blogService;
	private final PetRepository petRepository;
	private final PetStatusRepository petStatusRepository;

//	잠시 주석. 지우면안됨.
	@GetMapping("/{pageOwnerNickName}")
	public String update(@PathVariable String pageOwnerNickName, HttpServletRequest request, Model model, FoodDto blog,
			WalkDto walkDto, @AuthenticationPrincipal UserPrincipal userId, Pet pet) {
		System.out.println(pageOwnerNickName + "의 Blog입니다.");

		// Pet Entity 조회 관련 코드

		walkDto.setUserId(userId.getUserId()); // userId dto에 담기

		// dto의 userId와 엔티티의 useId는 서로 타입이 다르기 때문에 findByUserId가 아니라 findByUserUserId로
		// 메소드를 작성

		List<Pet> petList = petRepository.findByUserUserId(userId.getUserId()).stream().filter(entity -> entity != null)
				.collect(Collectors.toList());

		// 모델객체에 petList추가
		model.addAttribute("petList", petList);

		// 급식,급수,몸무게 게이지차트 데이터 받아오는 로직
		// petStatus와 비교할 오늘 날짜 데이터 만들기.
		LocalDateTime today = LocalDateTime.now();

		// 전체 petStatusList 가져오기
		List<PetStatus> petStatusList = petStatusRepository.findAll();

		// 추려진 List를 담을 리스트만들기
		List<PetStatus> todayPetStatusList = new ArrayList<>();

		// 권장식사량 차트 적용을 위해 세션에도 값을 담아주기
		HttpSession session = request.getSession();

		// petStatusList만큼 반복하여 날짜 데이터들을 비교
		for (PetStatus petStatus : petStatusList) {
			// 만약 날짜가 일치한다면, 해당 엔티티를 모델객체에 저장.
			if (petStatus.getRegDate().toLocalDate().equals(today.toLocalDate())) {
//				model.addAttribute("petStatusList",petStatus); // 이렇게 사용하면 값이 덮어씌워짐.
				todayPetStatusList.add(petStatus); // 각 petStatus를 새로운 리스트에 담아줌
				session.setAttribute("water", petStatus.getWater()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute("food", petStatus.getFood()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute("weight", petStatus.getWeight()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
			}
		}

		model.addAttribute("petStatusList", todayPetStatusList);

		return "/html/blog";
	}

	@PostMapping("/{pageOwnerNickName}/walk")
	@ResponseBody
	public String updateWalkData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody,
			WalkDto walkDto,@AuthenticationPrincipal UserPrincipal userId) throws JsonMappingException, JsonProcessingException {

		System.out.println("requestBody는 : " + requestBody);
		ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper로 RequestBody안에 담겨온 Json값을 자바객체로 변환
		JsonNode jsonNode = objectMapper.readTree(requestBody); // JsonNode로 get메소드 사용하고 objectMapper.readTree()로 JSON문자열을 파싱하여 JsonNode객체로 변환
		double walkDistance = jsonNode.get("TTD").asDouble();
		int walkTime = jsonNode.get("walkTime").asInt();
		JsonNode petNameListNode = jsonNode.get("petNameList"); // petNameList를 JsonNode객체에서 데이터를 추출하여 asText() 메서드를 사용하여 텍스트로 변환한 뒤 petNameList에 할당

		List<String> petNameList = new ArrayList<>(); // 변환한 jsonNode들받을 리스트 만들기

		if (petNameListNode.isArray()) { // 배열일 경우
			for (JsonNode node : petNameListNode) { // 배열요소 반복
				petNameList.add(node.asText());
			}
		}

		System.out.println("petNameList 는 : " + petNameList);
		
		
		LocalDateTime nowTime = LocalDateTime.now(); // regDate를 초기화할 변수선언
		walkDto.setUserId(pageOwnerNickName);
		walkDto.setRegDate(nowTime);
		walkDto.setWalkDistance(walkDistance);
		walkDto.setWalkTime(walkTime);
		
		List<PetStatus> petStatusList = petStatusRepository.findByUserUserId(walkDto.getUserId()); // 아이디와 일치하는 리스트로 1차 구분
//		System.out.println("petStatusList는 : "+petStatusList);
		
		if(petStatusList.isEmpty()) { // 아이디와 일치하는 petStatusList가 비어있을 경우
			for(int i = 0; i<petNameList.size();i++) {
				walkDto.setPetName(petNameList.get(i));
				blogService.createWalkStatus(walkDto);
			}
		} else { // 아이디와 일치하는 petStatusList가 비어있지 않은 경우
			for(int i =0;i<petNameList.size();i++) { // Pet에 등록된 반려동물의 마리수만큼 반복
				
				String petName = petNameList.get(i); // petName을 세팅하기위한 임시변수
//				System.out.println("petName은 : "+petName);
				List<PetStatus> filteredList = petStatusList.stream()
						.filter(petStatus -> petStatus.getPetName().contains(petName))
						.collect(Collectors.toList()); // petStatusList를 petName 을 포함한 List들로 필터링
				
				for(int j=0;j<filteredList.size();j++) { // filteredList 만큼 다시 반복
					if(filteredList.get(j).getRegDate().toLocalDate().equals(nowTime.toLocalDate())){ // 오늘날짜와 등록일자가 일치하면 update
//						System.out.println(filteredList.get(j));
						walkDto.setPetName(petName);
						
						blogService.updateWalkStatus(walkDto, filteredList.get(j));
						System.out.println("업데이트");
						
					}else { // 오늘날짜와 등록일자가 일치하지 않으면 create
//						System.out.println(filteredList.get(j));
						walkDto.setPetName(petName);
						blogService.createWalkStatus(walkDto);
						System.out.println("새로생성");
					}
				}
			}
			
		}

		return "redirect:blog/{pageOwnerNickName}";
	}

	@PostMapping("/{pageOwnerNickName}/food")
	@ResponseBody
	public String updateFoodData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody, FoodDto dto)
			throws JsonMappingException, JsonProcessingException {

		System.out.println("requestBody는 : " + requestBody);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(requestBody);
		int water = jsonNode.get("water").asInt();
		int food = jsonNode.get("food").asInt();
		double weight = jsonNode.get("weight").asDouble();

		JsonNode petNameListNode = jsonNode.get("petNameList");
		List<String> petNameList = new ArrayList<>();
		if (petNameListNode.isArray()) { // 배열일 경우
			for (JsonNode node : petNameListNode) { // 배열요소 반복
				petNameList.add(node.asText());
			}
		}

		dto.setFood(food);
		dto.setWater(water);
		dto.setWeight(weight);
		dto.setUserId(pageOwnerNickName);

		for (int i = 0; i < petNameList.size(); i++) {
//			System.out.println("petNameList.get(i) 는 : "+petNameList.get(i));
			dto.setPetName(petNameList.get(i));
			blogService.updateFoodStatus(dto);
		}

		return "redirect:blog/{pageOwnerNickName}";
	}

}