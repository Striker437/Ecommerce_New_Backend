package com.Ecommerce.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Ecommerce.Entity.User;



public class JwtUserDetails implements UserDetails {
	
	private User user;

	
	  public JwtUserDetails(User user)
	  { 
		  this.user = user;
		  }
	 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority Authorities=new SimpleGrantedAuthority(user.getRole());
		List<SimpleGrantedAuthority>list=new ArrayList<>();
		list.add(Authorities);
		return list;
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
		
	}

	@Override
	public String getUsername() {
		return user.getName();
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
