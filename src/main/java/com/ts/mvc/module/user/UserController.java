package com.ts.mvc.module.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
   
   @GetMapping("signup")
   public String signUp() {
      return "/html/signup";
   }

   @GetMapping("login")
   public String login() {
      return "/html/login";
   }
   
   @GetMapping("modify")
   public String userModify() {
      return "/html/user-modify";
   }
}