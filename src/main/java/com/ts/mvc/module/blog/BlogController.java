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
   public BlogDto distanceUpdate(@PathVariable String pageOwnerNickName, @RequestBody String totalDistance, BlogDto blog) {
	   blog.setTotalDistance(totalDistance);
	   System.out.println("총 산책거리는 "+blog.getTotalDistance());
	   // JSON형태로 데이터를 뿌려서 넘기기.
	   // Responsebody에 넘기기 => 더 알아보기
	   // msg를 하나 보내는 걸 고려해보아야함.
	   return blog;
   }
    
   @GetMapping("status")
   public String status() {
      return "/html/status";
   }


   

   
   

}