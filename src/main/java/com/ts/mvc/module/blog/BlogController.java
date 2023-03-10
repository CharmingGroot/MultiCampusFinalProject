package com.ts.mvc.module.blog;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ts.mvc.module.blog.dto.Blog;

import javassist.compiler.ast.Member;

@Controller
@RequestMapping("blog")
public class BlogController {
   
//   @GetMapping("")
//   public String blog() {
//      System.out.println("hi");
//      return "/html/blog";
//   }
   
   @GetMapping("")
   public String update(HttpServletRequest request, Model model, Blog blog){
      String water = request.getParameter("water");
      String food = request.getParameter("food");
      String weight = request.getParameter("weight");
      if(water != null || food != null || weight != null) {   
         model.addAttribute("water", water);
         System.out.println(blog.toString());
      }
      return "/html/blog";
   }

   @GetMapping("guestbook")
   public String guestbook() {
      return "/html/guestbook";
   }
   
   @GetMapping("diary")
   public String diary() {
      return "/html/diary";
   }
   
   @GetMapping("diary-modify")
   public String diaryMofdify() {
      return "/html/diary-modify";
   }
   
   @GetMapping("status")
   public String status() {
      return "/html/status";
   }

   

   
   

}