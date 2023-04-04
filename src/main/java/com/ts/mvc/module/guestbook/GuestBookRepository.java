package com.ts.mvc.module.guestbook;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;



@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long>{
	List<GuestBook> findAll();

	List<GuestBook> findByGbIdxContaining(Long gbIdx);

	Optional<GuestBook> findByGbIdx(Long i);

	List<GuestBook> findByUserUserId(String userId);

	List<GuestBook> findByPageOwnerAndUserUserId(String pageOwnerNickName, String userId);

	List<GuestBook> findByPageOwner(String pageOwnerNickName);




	

	
//	 @Query(value = "SELECT g FROM GuestBook g WHERE g.gbIdx > :idx ORDER BY g.gbIdx ASC")
//	    List<GuestBook> findNextGuestBooks(@Param("idx") Long idx);
}
