package com.ts.mvc.module.diary.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ts.mvc.module.diary.Diary;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.dto.Principal;

import lombok.Data;

@Data
public class DiaryUploadDto {

	private MultipartFile file;
	private String caption;

	
	public Diary toEntity(String postImageUrl, String userId) {
		return Diary.builder()
					.caption(caption)
					.postImageUrl(postImageUrl)
					.user(userId)
					.build();
	}
}
