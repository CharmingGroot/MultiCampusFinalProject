package com.ts.mvc.module.guestbook;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
import com.ts.mvc.module.guestbook.dto.response.GuestBookDetailResponse;
import com.ts.mvc.module.guestbook.dto.response.GuestBookListResponse;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("guestbook")
public class GuestBookController {
	
	
	private final GuestBookService guestBookService;
	private final GuestBookRepository guestBookRepository;
	
	
	@GetMapping("")
	public String guestbook(GuestBookListResponse dto,Long gbIdx, @PageableDefault(size=10, sort="gbIdx", direction = Direction.DESC, page = 0) Pageable pageable, Model model) {
		
//		Map<String, Object> commandMap = guestBookService.findGuestBookList(pageable);
//		model.addAllAttributes(commandMap);
		
		List<GuestBook> guestbookList = guestBookRepository.findAll()
				.stream()
				.filter(entity -> entity != null)
				.collect(Collectors.toList());
		
		model.addAttribute("guestbookList",guestbookList);
				
		return "/html/guestbook";
	}
	


	@PostMapping("upload")
	@ResponseBody
	public String upload(@RequestBody String content, GuestBookRegistRequest dto, Model model) {
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		dto.setContent(content);
		model.addAttribute("content", content);
		guestBookService.createGuestBook(dto);
		System.out.println(dto.getContent());
		return "redirect:/guestbook";
	}
	
	@PostMapping("remove")
	public String remove(Long bdIdx) {
		guestBookService.removeGuestBook(bdIdx, UserPrincipal.getUserPrincipal().getPrincipal());
		return "redirect:/board/list";
	}
}
