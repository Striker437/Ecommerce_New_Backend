package com.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.UserRepository;

@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/user")
	public List<User> getAllUser()
	{
	User user1=new User();
	user1.setName("Shivam Singh");
	user1.setAddress("Pratapgarh");
	user1.setPassword("12345");
	user1.setPhoneno(9793);
	user1.setRole("ROLE_USER");
	userRepository.save(user1);
	User user2=new User();
	user2.setName("Pankaj Singh");
	user2.setAddress("Kushinagar");
	user2.setPassword("1234567");
	user2.setPhoneno(343434);
	user2.setRole("ROLE_USER");
	userRepository.save(user2);
	User user3=new User();
	user3.setName("Anurag Soni");
	user3.setAddress("Kanpur");
	user3.setPassword("12345");
	user3.setPhoneno(976453);
	user3.setRole("ROLE_USER");
	userRepository.save(user3);
	List<User>userlist=userRepository.findAll();
	return userlist;
	
	
	}
	
	
	

}
