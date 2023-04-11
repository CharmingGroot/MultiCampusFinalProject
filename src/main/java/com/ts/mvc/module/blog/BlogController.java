package com.ts.mvc.module.blog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
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
		List<PetStatus> petStatusList = petStatusRepository.findByUserUserId(walkDto.getUserId());

		// 추려진 List를 담을 리스트만들기
		List<PetStatus> todayPetStatusList = new ArrayList<>();

		// 권장식사량 차트 적용을 위해 세션에도 값을 담아주기
		HttpSession session = request.getSession();

		// petStatusList만큼 반복하여 날짜 데이터들을 비교
		for (int i = 0; i<petStatusList.size(); i++) {
			System.out.println(petStatusList.get(i));
			// 만약 날짜가 일치한다면, 해당 엔티티를 모델객체에 저장.
			if(petStatusList.get(i).getRegDate().toLocalDate().equals(today.toLocalDate())) {
				todayPetStatusList.add(petStatusList.get(i));
				session.setAttribute(petStatusList.get(i).getPetName()+"Water", petStatusList.get(i).getWater()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute(petStatusList.get(i).getPetName()+"Food", petStatusList.get(i).getFood()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute(petStatusList.get(i).getPetName()+"Weight", petStatusList.get(i).getWeight()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute(petStatusList.get(i).getPetName()+"WalkDistance", petStatusList.get(i).getWalkDistance()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
				session.setAttribute(petStatusList.get(i).getPetName()+"WalkTime", petStatusList.get(i).getWalkTime()); // 얘는 마지막 값으로 덮어씌워져도 괜찮음.
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
				
//				System.out.println("filteredList은 : "+filteredList);
//				System.out.println("filteredList.size()은 : "+filteredList.size());

				for(int j=filteredList.size()-1;j>=0;j--) { // filteredList 만큼 다시 반복
					if(filteredList.get(j).getPetName().equals(petName) && filteredList.get(j).getRegDate().toLocalDate().equals(nowTime.toLocalDate())){ // j번째 펫상태정보의 오늘날짜와 등록일자가 일치하면 update
//						System.out.println(filteredList.get(j));
						System.out.println("if의 "+j);
						walkDto.setPetName(petName);
						blogService.updateWalkStatus(walkDto, filteredList.get(j));
						
						System.out.println("업데이트");
						break; // 값중복업데이트 방지 ... 최상위 반복문의 반복횟수때문에 break걸어줘야함.
					}else if(filteredList.get(j).getPetName().equals(petName) && (filteredList.get(j).getRegDate().toLocalDate()).isBefore(nowTime.toLocalDate())){ // 등록일자가 과거라면, create
//						System.out.println(filteredList.get(j));
						System.out.println("else의 "+j);
						walkDto.setPetName(petName);
						blogService.createWalkStatus(walkDto);
						System.out.println("새로생성");
						break;
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
		
		List<PetStatus> petStatusList = petStatusRepository.findByUserUserId(dto.getUserId()); // 같은 아이디를 가진 모든 petStatus 필드리스트
		
		LocalDateTime nowTime = LocalDateTime.now(); // 날짜 비교용
		
		// 체크된 펫의 수만큼 반복
		for(int i = 0; i<petNameList.size(); i++) {
			System.out.println("현재 탐색중인 pet : " + petNameList.get(i));
			
			if(petStatusList.isEmpty()) { // 비어있다면
				dto.setPetName(petNameList.get(i)); // 하나 생성
				blogService.createFeedStatus(dto);
			} else { // 비어있지 않다면 날짜 비교하고 업데이트
				// petStatusList 반복
				for(int j =petStatusList.size()-1; j>=0;j--) { // petStatusList 는 userId와 일치하는 모든 데이터임.  펫이름이 다를수도있고, 등록일자가 오늘의 이전일 수 있음.
					
					System.out.println("=============");
					System.out.println("현재 인덱스 :"+ j);
					System.out.println("현재 펫 :"+ petStatusList.get(j).getPetName());
					System.out.println("=============");
					
					if(petStatusList.get(j).getPetName().equals(petNameList.get(i)) && petStatusList.get(j).getRegDate().toLocalDate().equals(nowTime.toLocalDate())) { // 현재인덱스의 펫 이름과 등록날짜가 같아야만 업데이트
						System.out.println("=============");
						System.out.println("날짜와 펫이름이 같음");
						System.out.println("현재인덱스는 : "+j);
						System.out.println("petNameList.get(i)는 : "+petNameList.get(i));
						System.out.println("petStatusList.get(j).getWeight()는 : "+petStatusList.get(j).getWeight());
						
						dto.setPetName(petNameList.get(i));
						System.out.println("=============");
						blogService.updateFeedStatus(petStatusList.get(j),dto);
						break;
					}
					else if(petStatusList.get(j).getPetName().equals(petNameList.get(i)) && (petStatusList.get(j).getRegDate().toLocalDate()).isBefore(nowTime.toLocalDate())){ // 현재 인덱스의 펫이름이 일치하고 등록 날짜가 과거임.
						System.out.println("=============");
						System.out.println("날짜는 과거, 펫이름은 같음");
						System.out.println("현재인덱스는 : "+j);
						System.out.println("petNameList.get(i)는 : "+petNameList.get(i));
						dto.setPetName(petNameList.get(i)); // 하나 생성
						blogService.createFeedStatus(dto);
						System.out.println("=============");
//						break;
					}
				}
			}
		};

		return "redirect:blog/{pageOwnerNickName}";
	}

}