package com.ts.mvc.module.guestbook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long>{
	List<GuestBook> findAll();

	List<GuestBook> findByGbIdxContaining(Long gbIdx);
	
//	 @Query(value = "SELECT g FROM GuestBook g WHERE g.gbIdx > :idx ORDER BY g.gbIdx ASC")
//	    List<GuestBook> findNextGuestBooks(@Param("idx") Long idx);
}
