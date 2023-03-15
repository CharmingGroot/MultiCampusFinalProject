package com.ts.mvc.module.user;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.ts.mvc.module.user.dto.Principal;

import lombok.Getter;

@Getter
public class UserPrincipal extends User{
	
	private static final long serialVersionUID = 1L;
	
	private final Principal principal;

	public UserPrincipal(Principal principal) {
		super(	principal.getEmail(),
				principal.getPassword(),
				List.of(new SimpleGrantedAuthority(principal.getGrade())));
		
		this.principal = principal;
	}
	
	public static UserPrincipal getUserPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
	public String getPassword() {
		return principal.getPassword();
	}
	
	public String getEmail() {
		return principal.getEmail();
	}
	
	public String getTell() {
		return principal.getTell();
	}
	
	public String getUserName() {
		return principal.getUserName();
	}
	
	public String getGrade() {
		return principal.getGrade();
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	

}
