package com.ts.mvc.module.blog.Controller;



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

import com.ts.mvc.module.blog.Dto.Blog;

import javassist.compiler.ast.Member;

@Controller
@RequestMapping("blog")
public class BlogController {
	
//	@GetMapping("")
//	public String blog() {
//		System.out.println("hi");
//		return "/html/blog";
//	}
	
	@GetMapping("")
	public String update(HttpServletRequest request, Model model){
		String water = request.getParameter("water");
		if(water != null) {
			System.out.println(water);			
			model.addAttribute("water", water);
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
	
	@GetMapping("status")
	public String status() {
		return "/html/status";
	}
	
		
	
	

	

	
	

}
