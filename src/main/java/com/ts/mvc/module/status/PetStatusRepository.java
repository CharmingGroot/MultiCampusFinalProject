package com.ts.mvc.module.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetStatusRepository extends JpaRepository<PetStatus, Long>{

}
