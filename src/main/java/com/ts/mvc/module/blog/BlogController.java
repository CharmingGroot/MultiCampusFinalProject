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

import com.ts.mvc.module.blog.dto.BlogDto;
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
   public String updateTodayData(@PathVariable String pageOwnerNickName, @RequestBody String requestBody, BlogDto blog) {
	   blog.setTotalDistance(requestBody);
	   System.out.println("총 산책거리는 "+blog.getTotalDistance());
	   
	   
	   
	   
	   return "redirect:blog/{pageOwnerNickName}";
   }
    
   @GetMapping("status")
   public String status() {
      return "/html/status";
   }


   

   
   

}