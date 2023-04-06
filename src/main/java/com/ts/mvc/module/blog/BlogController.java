package com.ts.mvc.module.blog;



import javax.servlet.http.HttpServletRequest;

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
import com.ts.mvc.module.status.dto.PetStatusDto;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("blog")
@AllArgsConstructor
public class BlogController {
   
   private final GuestBookService guestBookService;
	
   @GetMapping("/{pageOwnerNickName}")
   public String update(@PathVariable String pageOwnerNickName, HttpServletRequest request, Model model, BlogDto blog){
	  System.out.println(pageOwnerNickName + "의 Blog입니다.");
	  
	  // 1. 소유자만 접근가능하도록 서비스로직 작성
	  
	  // 2. petStaus ( feed 관련 ) 가져오는 로직 작성
	  
	  // 3. 주간일정 가져오는 로직 작성
	  
	  
	  
      String water = request.getParameter("water");
      String food = request.getParameter("food");
      String weight = request.getParameter("weight");
      
      if(water != null || food != null || weight != null) {   
         model.addAttribute("water", water);
         System.out.println(blog.toString());
      }
      return "/html/blog";
   }
   
   @PostMapping("/{pageOwnerNickName}")
   @ResponseBody
   public String updateTodayData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody, WalkDto dto) throws JsonMappingException, JsonProcessingException {
	   
	   System.out.println("requestBody는 : "+ requestBody);
	   
	   // ObjectMapper로 RequestBody안에 담겨온 Json값을 자바객체로 변환
	   ObjectMapper objectMapper = new ObjectMapper();
	   // JsonNode로 get메소드 사용하고  objectMapper.readTree()로 JSON문자열을 파싱하여 JsonNode객체로 변환
	   JsonNode jsonNode = objectMapper.readTree(requestBody);
	   double walkDistance = jsonNode.get("TTD").asDouble();
	   int walkTime = jsonNode.get("walkTime").asInt();
	   
	   // RequestBody로 받아온 객체를 dto에 할당
	   dto.setWalkDistance(walkDistance);
	   dto.setWalkTime(walkTime);
	   dto.setUser(pageOwnerNickName);
	   dto.setPet(requestBody);
	   
	   
	   
	   // CRUD - C
	   // 1. Pet엔티티의 petIdx를 조회
	   
	   
	   
	   return "redirect:blog/{pageOwnerNickName}";
   }
    
   @GetMapping("status")
   public String status() {
      return "/html/status";
   }


   

   
   

}