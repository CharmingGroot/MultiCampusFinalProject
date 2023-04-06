package com.ts.mvc.module.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.status.PetStatus;

@Repository
public interface BlogRepository extends JpaRepository<PetStatus, Long>{
	
}
