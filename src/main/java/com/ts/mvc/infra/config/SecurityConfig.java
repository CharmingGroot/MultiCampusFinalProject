package com.ts.mvc.infra.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ts.mvc.infra.config.oauth.OAuth2DetailsService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity // Spring Security의 기본 설정 대신 사용자가 커스터마이징한 설정을 시큐리티에 적용
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	private final DataSource datsSource;
	private final UserDetailsService userDetailsService;
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(datsSource);
		
		return tokenRepository;
	}
	
	@Bean                                                            
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/user/signup", "/user/checkId", "/user/signupimpl/**").permitAll()
		.antMatchers(HttpMethod.POST,"/user/signup").permitAll()
		.antMatchers(HttpMethod.POST, "/mail").permitAll()
//		.antMatchers(HttpMethod.GET,"/board/list", "/board/detail", "/board/download").permitAll()
//		.antMatchers(HttpMethod.GET, "/admin").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/user/login") // get
			.loginProcessingUrl("/user/login") // post
			.usernameParameter("userId")
			.defaultSuccessUrl("/")
//			.successHandler(authSuccessHandler)
//			.failureHandler(authFailureHandler)
			.permitAll()
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(oAuth2DetailsService);
		
		http.logout()
			.logoutUrl("/user/logout")
			.logoutSuccessUrl("/");
		
		http.rememberMe()
			.userDetailsService(userDetailsService)
			.tokenRepository(tokenRepository());
		
		
		
		// csrf : post요청일 때 수행해야 하는 csrf 토큰 검증을 끔
		//http.csrf().disable();
		http.csrf().ignoringAntMatchers("/mail");
		http.csrf().ignoringAntMatchers("/blog");
		http.csrf().ignoringAntMatchers("/guestbook/upload");
		http.csrf().ignoringAntMatchers("/guestbook/update");
		http.csrf().ignoringAntMatchers("/guestbook/delete");
		http.csrf().disable().cors(); // ajax 사용하면서 put 오류나는거때문에...
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().antMatchers("/assets/**", "/css/**", "/fonts/**", "/icon/**", "/js/**", "/scss/**")
						 .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	

}
