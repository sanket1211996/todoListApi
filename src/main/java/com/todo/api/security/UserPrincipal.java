package com.todo.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.todo.api.model.User;

public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6546019165030368210L;

	User user;

	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		user.getPersmissionList().forEach(  p -> {
			GrantedAuthority  grantedAuthority =  new SimpleGrantedAuthority(p);
			grantedAuthorities.add(grantedAuthority);
		});
		
		user.getRoleList().forEach(  r -> {
			GrantedAuthority  grantedAuthority =  new SimpleGrantedAuthority("ROLE_" + r);
			grantedAuthorities.add(grantedAuthority);
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
		return user.isActive();
	}

}
