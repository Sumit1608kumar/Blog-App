package com.BlogApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.dto.UserDto;
import com.BlogApp.entities.User;
import com.BlogApp.exceptions.CustomException;
import com.BlogApp.repositories.UserRepository;

@RestController
public class AuthenticationController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modeMapper;
	
	@PostMapping("/api/login")
	public ResponseEntity<UserDto> performLogin(@RequestBody User user){
		User founduser = userRepository.findUserByUsernameAndPassword(user.getUsername(),user.getPassword()).orElseThrow(() -> new CustomException("Invalid Credentials !!",
						HttpStatus.UNAUTHORIZED));
		return new ResponseEntity<UserDto>( modeMapper.map(founduser,UserDto.class),HttpStatus.OK);
	}
}
