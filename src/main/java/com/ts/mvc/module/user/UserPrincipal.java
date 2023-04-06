package com.ts.mvc.module.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ts.mvc.module.user.dto.Principal;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails, OAuth2User{
	 
	private static final long serialVersionUID = 1L;
	
	private Principal principal;
	
	public UserPrincipal(Principal principal) {
		this.principal = principal;
	}

	// 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> {return principal.getGrade();});
		
		return collector;
	}
	
	public static UserPrincipal getUserPrincipal() {
		return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public String getUserId() {
		return principal.getUserId();
	}
	
	@Override
	public String getPassword() {
		return principal.getPassword();
	}
	
	@Override
	public String getUsername() {
		return principal.getUserId();
	}
	
	public String getNickname() {
		return principal.getNickname();
	}
	
	public String getEmail() {
		return principal.getEmail();
	}
	
	public String getProfileImageUrl() {
		return principal.getProfileImageUrl();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



	



	
	
	

}
