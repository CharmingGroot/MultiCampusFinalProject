package com.ts.mvc.module.status;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.status.dto.PetEventDto;
import com.ts.mvc.module.user.User;

@Repository
public interface PetEventRepository extends JpaRepository<PetEvent, Long>{

	void save(PetEventDto petEventDto);
	                                                   // <entity, id값의 데이터 타입>
}
