package com.ts.mvc.module.diary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.exception.AuthException;
import com.ts.mvc.infra.exception.CustomException;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.module.diary.dto.DiaryUploadDto;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserPrincipal;
import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.api.dto.UserProfileDto;
import com.ts.mvc.module.user.dto.Principal;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiaryService {
	
	private final UserRepository userRepository;
	private final DiaryRepository diaryRepository;
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public void diaryImageUpload(DiaryUploadDto diaryUploadDto, UserPrincipal userPrincipal) throws IOException {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + diaryUploadDto.getFile().getOriginalFilename();
//		System.out.println("이미지 파일이름: " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		Path parentPath = imageFilePath.getParent(); // imageFilePath의 상위 디렉토리 경로를 가져옴

		if (parentPath != null && !Files.exists(parentPath)) { // 상위 디렉토리가 존재하지 않는 경우
		    Files.createDirectories(parentPath); // 상위 디렉토리를 생성함
		}

		Files.createFile(imageFilePath); // 파일을 생성함
		
		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, diaryUploadDto.getFile().getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Diary diary = diaryUploadDto.toEntity(imageFileName, userPrincipal.getUserId());
		diaryRepository.save(diary);
		
//		System.out.println(diaryEntity);
	}
	
	@Transactional(readOnly = true)
	public UserProfileDto diaryForm(String pageUserId, String principalId) {
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
			throw new CustomException("해당 다이어리는 없는 페이지입니다.");
		});
		
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserId.equals(principalId)); // 같으면 true
		dto.setImageCount(userEntity.getImages().size());
		
		return dto;
	}

	@Transactional
	public void removeDiary(Long dyIdx, String principalId) {
		Diary diary = diaryRepository.findById(dyIdx).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!diary.getUser().equals(principalId)) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		diaryRepository.delete(diary);
	}


}
