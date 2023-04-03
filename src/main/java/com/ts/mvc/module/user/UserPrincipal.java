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
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
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
		return user.getUserId();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	public String getNickname() {
		return user.getNickname();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
	
	public String getProfileImageUrl() {
		return user.getProfileImageUrl();
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
