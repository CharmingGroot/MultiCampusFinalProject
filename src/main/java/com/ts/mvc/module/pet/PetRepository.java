package com.ts.mvc.module.pet;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ts.mvc.module.user.User;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

//	List<Pet> findByUser(User user);

//	List<Pet> findByUser(User user);

//	List<Pet> findByUserUserId(String userId);

//	List<Pet> findByUserId(String userId);

	List<Pet> findByUserUserId(String userId);



}
