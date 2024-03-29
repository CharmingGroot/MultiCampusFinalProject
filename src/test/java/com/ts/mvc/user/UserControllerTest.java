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
	
	// 더미데이터 만들기
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testsignUpImpl() throws Exception {
		SignUpRequest form = new SignUpRequest();
		form.setUserId("happy");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("domeng@naver.com");
		form.setNickname("해피");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testsignUpImplS() throws Exception {
		SignUpRequest form = new SignUpRequest();
		form.setUserId("hoho");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("chlghrms2@naver.com");
		form.setNickname("호구닝");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	}
	
	
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testSignUpImplD() throws Exception {
		
		SignUpRequest form = new SignUpRequest();
		form.setUserId("group1B");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("test239078@naver.com");
		form.setNickname("test1B");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testSignUpImplC() throws Exception {
		
		SignUpRequest form = new SignUpRequest();
		form.setUserId("group1A");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("test239078@naver.com");
		form.setNickname("test1A");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testSignUpImplB() throws Exception {
		
		SignUpRequest form = new SignUpRequest();
		form.setUserId("group2B");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("test239078@naver.com");
		form.setNickname("test2B");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	
	}
	
	@Test
	@DisplayName("회원가입 정보 저장")
	public void testSignUpImplA() throws Exception {
		
		SignUpRequest form = new SignUpRequest();
		form.setUserId("group2A");
		form.setPassword("123qwe!@#QWE");
		form.setEmail("test239078@naver.com");
		form.setNickname("test2A");
		form.setGrade("ROLE_ADMIN");
		
		mockMvc.perform(get("/user/signupimpl/1234")
				.sessionAttr("signupForm", form)
				.sessionAttr("authToken", "1234"))
				.andExpect(status().is3xxRedirection());
	
	}
}
