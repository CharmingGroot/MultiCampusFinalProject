package com.ts.mvc.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ts.mvc.module.user.dto.request.SignUpRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testSendAuthenticateMail() throws Exception {
		mockMvc.perform(post("/user/signup")
				.param("email", "eedomeng@gmail.com")
				.param("password", "1234")
				.param("userName", "ldm")
				.param("tell", "010-0119-0112"))
		.andDo(print());
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testsignUpImpl() throws Exception {
		SignUpRequest form = new SignUpRequest();
		form.setUserName("test");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("eedomeng@naver.com");
		form.setTell("010-0000-0112");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	}
}
