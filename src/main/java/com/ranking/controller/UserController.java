package com.ranking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.interfaces.UserRepository;
import com.ranking.model.UserApplication;;
 
@RestController
@RequestMapping("/users") 
public class UserController {
 
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	@PostMapping("/signUp")
	
	public ResponseEntity<?> signUp(@RequestBody UserApplication user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(userRepository.save(user),HttpStatus.CREATED) ;
	}

	@GetMapping("")
    public List<UserApplication> getTasks() {
        return userRepository.findAll();
    }
 
}