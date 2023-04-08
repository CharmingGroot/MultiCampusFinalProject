package com.ts.mvc.module.pet;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.user.User;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{


	List<Pet> findByUserUserId(String userId);

	Optional<Pet> findByPetName(String petName);


}
