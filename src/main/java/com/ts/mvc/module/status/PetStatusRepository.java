package com.ts.mvc.module.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

@Repository
public interface PetStatusRepository extends JpaRepository<PetStatus, Long>{

	Optional<Pet> findByPetName(String pet);

	List<PetStatus> findByPetNameAndUserUserId(String petName,String userId);

	List<PetStatus> findByUserUserId(String userId);


	

//	List<PetStatus> findByPetStatusPetName(String petName);

}
