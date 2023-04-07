package com.ts.mvc.module.status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

@Repository
public interface PetStatusRepository extends JpaRepository<PetStatus, Long>{

	Optional<Pet> findByPetName(String pet);

}
