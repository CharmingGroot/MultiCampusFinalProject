package com.ts.mvc.pet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.mvc.module.pet.Pet;
import com.ts.mvc.module.user.User;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	public void testAddPet() throws Exception {
//		// 더미 유저 생성
//		User user = new User();
//		user.setUserId("testUser");
//		user.setPassword("password");
//		user.setEmail("testUser@gmail.com");
//		user.setNickname("testUser");
//		user.setGrade("ROLE_USER");
//		
//		// 더미 펫 생성
//		Pet pet = new Pet();
//		pet.setPetName("초코");
//		pet.setPetBirthdate("2022-01-01");
//		pet.setBreed("말티즈");
//		pet.setPetNumber("123456789");
//		pet.setWeight(2.5);
//		pet.setGender(true);
//		pet.setIsNeutered(false);
//		pet.setDogMbti("ESTP");
//		pet.setUser(user);
//		
//		// POST 요청을 보내서 더미 데이터 저장
//		mockMvc.perform(post("/pet")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(pet)))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.petName").value("초코"))
//				.andExpect(jsonPath("$.breed").value("말티즈"))
//				.andExpect(jsonPath("$.weight").value(2.5))
//				.andExpect(jsonPath("$.user.userId").value("testUser"));
//	}
	
}
