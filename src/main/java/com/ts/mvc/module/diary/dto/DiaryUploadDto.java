package com.ts.mvc.module.diary.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ts.mvc.module.diary.Diary;
import com.ts.mvc.module.user.User;

import lombok.Data;

@Data
public class DiaryUploadDto {

	private MultipartFile file;
	private String caption;
	
	public Diary toEntity(String postImageUrl, User user) {
		return Diary.builder()
					.caption(caption)
					.postImageUrl(postImageUrl)
					.user(user)
					.build();
	}
}
