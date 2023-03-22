package com.ts.mvc.module.guestbook;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ts.mvc.infra.code.ErrorCode;
import com.ts.mvc.infra.exception.AuthException;
import com.ts.mvc.infra.exception.HandlableException;
import com.ts.mvc.module.guestbook.dto.request.GuestBookRegistRequest;
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

	@Transactional
	public void removeGuestBook(Long gbIdx, Principal principal) {
		GuestBook guestBook = guestBookRepository.findById(gbIdx).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!guestBook.getUser().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		guestBookRepository.delete(guestBook);
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
