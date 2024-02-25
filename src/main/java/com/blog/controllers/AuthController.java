package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.security.JwtAuthRequest;
import com.blog.security.JwtAuthResponse;
import com.blog.security.JwtTokenHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	 public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request) {

	        authenticate(request.getUsername(), request.getPassword());


	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
	        String token = jwtTokenHelper.generateToken(userDetails);

	        JwtAuthResponse response = JwtAuthResponse.builder()
	        		.token(token)
	                .username(userDetails.getUsername()).build();
	        
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

	private void authenticate(String username, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionalHandler() {
		return "Invalid Credentials!!";
	}
}
