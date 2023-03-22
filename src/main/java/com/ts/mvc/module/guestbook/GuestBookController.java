package com.ts.mvc.module.guestbook;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
import com.ts.mvc.module.guestbook.dto.response.GuestBookDetailResponse;
import com.ts.mvc.module.user.UserPrincipal;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("guestbook")
public class GuestBookController {
	
	private final GuestBookService guestBookService;
	
	@GetMapping("")
	public String guestbook(Long gbIdx, @PageableDefault(size=10, sort="gbIdx", direction = Direction.DESC, page = 0) Pageable pageable, Model model) {
		
		Map<String, Object> commandMap = guestBookService.findGuestBookList(pageable);
		model.addAllAttributes(commandMap);
		
//		GuestBookDetailResponse dto = guestBookService.findGuestBookByGbIdx(gbIdx);
//		model.addAttribute("guestbook", dto);
		
		return "/html/guestbook";
	}


	@PostMapping("upload")
	public String upload(GuestBookRegistRequest dto) {
		
		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		guestBookService.createGuestBook(dto);
		
		return "redirect:/";
	}
	
	@PostMapping("remove")
	public String remove(Long bdIdx) {
		guestBookService.removeGuestBook(bdIdx, UserPrincipal.getUserPrincipal().getPrincipal());
		return "redirect:/board/list";
	}
}
