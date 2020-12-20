package com.Ecommerce.Controller;

import java.security.Principal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Config.JwtTokenUtil;
import com.Ecommerce.Entity.JwtRequest;
import com.Ecommerce.Entity.JwtResponse;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.UserRepository;





//generate the token
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());    //authenticate the user whether user has correct credentials

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		
		System.out.println("get username in token generation"+userDetails.getUsername());
		User user=userRepository.findByName(userDetails.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);            //generate the token from jwt util class
		System.out.println("token: "+token);

		return new JwtResponse(token,user);
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
