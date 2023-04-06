package com.ts.mvc.module.diary;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ts.mvc.infra.exception.CustomValidationException;
import com.ts.mvc.module.diary.dto.DiaryUploadDto;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserService;
import com.ts.mvc.module.user.api.dto.UserProfileDto;
import com.ts.mvc.module.user.dto.Principal;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@GetMapping("/{pageUserId}")
	public String diary(@PathVariable String pageUserId, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		UserProfileDto dto = diaryService.diaryForm(pageUserId, userPrincipal.getUserId());
		
		model.addAttribute("dto", dto);
		
		return "/diary/diary";
	}

	@GetMapping("form")
	public String diaryCreate() {
		return "/diary/diary-form";
	}
	
	@PostMapping("/upload")
	public String imageUpload(DiaryUploadDto diaryUploadDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
		if(diaryUploadDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 첨부되지 않았습니다", null);
		}
		
		// 서비스 호출
		diaryService.diaryImageUpload(diaryUploadDto, userPrincipal);		
		
		return "redirect:/diary/" + userPrincipal.getUserId();
	}
	
	@PostMapping("remove")
	public String diaryRemove(Long dyIdx, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		diaryService.removeDiary(dyIdx, UserPrincipal.getUserPrincipal().getUserId());
		
		return "redirect:/diary/" + userPrincipal.getUserId();
	}
	
	
}
