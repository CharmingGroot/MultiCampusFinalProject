package com.ts.mvc.module.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.mvc.module.blog.dto.request.FoodDto;
import com.ts.mvc.module.blog.dto.request.WalkDto;
import com.ts.mvc.module.guestbook.GuestBookService;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.pet.PetRepository;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("blog")
@AllArgsConstructor
public class BlogController {

	private final BlogService blogService;
	private final PetRepository petRepository;

//	잠시 주석. 지우면안됨.
	@GetMapping("/{pageOwnerNickName}")
	public String update(@PathVariable String pageOwnerNickName,HttpServletRequest request, Model model, FoodDto blog,
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

		
		
		// 급식,급수,몸무게 게이지차트 데이터 받아오기
		
		
		
		String water = request.getParameter("water");
		String food = request.getParameter("food");
		String weight = request.getParameter("weight");

		if (water != null || food != null || weight != null) {
			model.addAttribute("water", water);
			System.out.println(blog.toString());
		}
		return "/html/blog";
	}
	
	
	

	@PostMapping("/{pageOwnerNickName}/walk")
	@ResponseBody
	public String updateWalkData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody, WalkDto walkDto, FoodDto foodDto)
			throws JsonMappingException, JsonProcessingException {

		System.out.println("requestBody는 : " + requestBody);

		
		// ObjectMapper로 RequestBody안에 담겨온 Json값을 자바객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		// JsonNode로 get메소드 사용하고 objectMapper.readTree()로 JSON문자열을 파싱하여 JsonNode객체로 변환
		JsonNode jsonNode = objectMapper.readTree(requestBody);
		double walkDistance = jsonNode.get("TTD").asDouble();
		int walkTime = jsonNode.get("walkTime").asInt();
		
		// petNameList를 JsonNode객체에서 데이터를 추출하여 asText() 메서드를 사용하여 텍스트로 변환한 뒤 petNameList에 할당
		JsonNode petNameListNode = jsonNode.get("petNameList");
		List<String> petNameList = new ArrayList<>();
		if(petNameListNode.isArray()) { // 배열일 경우
			for(JsonNode node : petNameListNode) { // 배열요소 반복
				petNameList.add(node.asText());
			}
		}

		System.out.println("petNameList 는 : "+ petNameList);
		
		
		
		// RequestBody로 받아온 객체를 dto에 할당
		walkDto.setWalkDistance(walkDistance);
		walkDto.setWalkTime(walkTime);
		walkDto.setUserId(pageOwnerNickName);
//		dto.setPetName("완두콩");

		
		// 1. createPetStatus실행 petNameList의 배열length만큼 돌리기.
		for(int i = 0; i<petNameList.size();i++) {
			System.out.println("petNameList.get(i) 는 : "+petNameList.get(i));
			walkDto.setPetName(petNameList.get(i));
			blogService.createWalkStatus(walkDto,foodDto);
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
		if(petNameListNode.isArray()) { // 배열일 경우
			for(JsonNode node : petNameListNode) { // 배열요소 반복
				petNameList.add(node.asText());
			}
		}
		
		dto.setFood(food);
		dto.setWater(water);
		dto.setWeight(weight);
		dto.setUserId(pageOwnerNickName);

		
		for(int i = 0; i<petNameList.size();i++) {
//			System.out.println("petNameList.get(i) 는 : "+petNameList.get(i));
			dto.setPetName(petNameList.get(i));
			blogService.updateFoodStatus(dto);
		}

		return "redirect:blog/{pageOwnerNickName}";
	}

	@GetMapping("status")
	public String status() {
		return "/html/status";
	}

}