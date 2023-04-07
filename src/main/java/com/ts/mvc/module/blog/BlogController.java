package com.ts.mvc.module.blog;

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
import com.ts.mvc.module.blog.dto.BlogDto;
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

	@GetMapping("/{pageOwnerNickName}")
	public String update(@PathVariable String pageOwnerNickName, HttpServletRequest request, Model model, BlogDto blog,
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

		String water = request.getParameter("water");
		String food = request.getParameter("food");
		String weight = request.getParameter("weight");

		if (water != null || food != null || weight != null) {
			model.addAttribute("water", water);
			System.out.println(blog.toString());
		}
		return "/html/blog";
	}

	@PostMapping("/{pageOwnerNickName}")
	@ResponseBody
	public String updateTodayData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody, WalkDto dto)
			throws JsonMappingException, JsonProcessingException {

		System.out.println("requestBody는 : " + requestBody);

		// ObjectMapper로 RequestBody안에 담겨온 Json값을 자바객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		// JsonNode로 get메소드 사용하고 objectMapper.readTree()로 JSON문자열을 파싱하여 JsonNode객체로 변환
		JsonNode jsonNode = objectMapper.readTree(requestBody);
		double walkDistance = jsonNode.get("TTD").asDouble();
		int walkTime = jsonNode.get("walkTime").asInt();

		// RequestBody로 받아온 객체를 dto에 할당
		dto.setWalkDistance(walkDistance);
		dto.setWalkTime(walkTime);
		dto.setUserId(pageOwnerNickName);
		dto.setPetName("임시 펫이름");

		// 1. createPetStatus실행
		blogService.createStatus(dto);

		return "redirect:blog/{pageOwnerNickName}";
	}

	@GetMapping("status")
	public String status() {
		return "/html/status";
	}

}