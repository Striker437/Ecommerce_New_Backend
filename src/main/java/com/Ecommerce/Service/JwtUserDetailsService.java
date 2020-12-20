package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ecommerce.Repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		com.Ecommerce.Entity.User user=userRepository.findByName(username);  //get user from databse
		System.out.println("user "+user.toString());
		
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		else
		{
			return new JwtUserDetails(user);    //return userdetails
		}
		/*
		 * if ("shivam".equals(username)) { return new User("shivam", "shivam123", new
		 * ArrayList<>()); } else { throw new
		 * UsernameNotFoundException("User not found with username: " + username); }
		 */
	}

}