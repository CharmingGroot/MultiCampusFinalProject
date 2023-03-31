package com.ts.mvc.module.guestbook;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.exception.AuthException;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.module.guestbook.dto.request.GuestBookDeleteRequest;
import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
import com.ts.mvc.module.guestbook.dto.request.GuestBookUpdateRequest;
import com.ts.mvc.module.guestbook.dto.response.GuestBookDetailResponse;
import com.ts.mvc.module.guestbook.dto.response.GuestBookListResponse;
import com.ts.mvc.module.user.User;
import com.ts.mvc.module.user.UserRepository;
import com.ts.mvc.module.user.dto.Principal;
import com.ts.mvc.module.guestbook.Paging;

import lombok.AllArgsConstructor;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class GuestBookService {
	
	private final UserRepository userRepository;
	private final GuestBookRepository guestBookRepository;

	@Transactional
	public void createGuestBook(GuestBookRegistRequest dto) {
		User user = userRepository.findById(dto.getUserId()).get();
		GuestBook guestBook = GuestBook.createGuestBook(dto, user);
		
		guestBookRepository.saveAndFlush(guestBook);
		
	}
	
	
	// 수정기능
	// 매개변수는 dto가 되는 것이 맞음.
	@Transactional
	public void updateGuestBook(GuestBookUpdateRequest dto) {
		
		// 칼럼조회 dto가 받아온 gbIdx와 일치하는 엔티티를 조회
		Optional<GuestBook> guestbook = guestBookRepository.findById(dto.getGbIdx());
		
		GuestBook guestBook = guestbook.get();
		
		guestBook.setContent(dto.getContent());
		
		
		// save n flush
		guestBookRepository.saveAndFlush(guestBook);
		
				
		
		System.out.println("updateGuestBook의 결과값"+ guestBook);
//		System.out.println("guestBook.getUser의 결과값" +guestBook.getUser()); // set 안해줘서 null
//		System.out.println("guestBook.getGbIdx의 결과값" +guestBook.getGbIdx());
		System.out.println("updateGuestBook Service 실행");
		
	}

	@Transactional
	public void removeGuestBook(Long gbIdx, Principal principal) {
		GuestBook guestBook = guestBookRepository.findById(gbIdx).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!guestBook.getUser().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		guestBookRepository.delete(guestBook);
	}

	@Transactional
	public void deleteGuestBook(GuestBookDeleteRequest dto) {
		try {
			GuestBook guestBook = guestBookRepository.findById(dto.getGbIdx()).orElse(null); // 먼저 해당 Entity를 조회합니다.
			System.out.println("Service Layer : Delete : "+guestBook);
			
			// 삭제할 gbIdx 행 다음 순서의 글들을 조회 >> gbIdx가 primary key로 설정되어있어서 변경이 불가함.
//            List<GuestBook> nextGuestBooks = guestBookRepository.findNextGuestBooks(dto.getGbIdx());

//            for(GuestBook guestbook : nextGuestBooks) {
//            	guestbook.setGbIdx(guestbook.getGbIdx()-1);
//            }
			
			
			guestBookRepository.delete(guestBook); // 조회된 Entity를 delete 메소드에 전달합니다.			
		} catch (Exception e) {
			System.out.println("게시글이 존재하지 않습니다.");
		}
		
		
	}

	
	
	public Map<String, Object> findGuestBookList(Pageable pageable) {
		Page<GuestBook> page = guestBookRepository.findAll(pageable);
		
		Paging paging = Paging.builder()
							  .page(page)
							  .blockCnt(5)
							  .build();
		
		return Map.of("guestBookList", GuestBookListResponse.toDtoList(page.getContent()), "paging", paging);
	}



	

//	public GuestBookDetailResponse findGuestBookByGbIdx(Long gbIdx) {
//		GuestBook guestBook = guestBookRepository.findById(gbIdx)
//					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		
//		return new GuestBookDetailResponse(guestBook);
//	}

	

}
