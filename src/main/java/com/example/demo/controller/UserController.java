package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserRequestLogin;
import com.example.demo.service.user.LoginService;
import com.example.demo.service.user.RegisterService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
   private  RegisterService registerService;
    
	@Autowired
	private LoginService loginService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
		
		registerService.createUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
	}
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserRequestLogin userRequestLogin){
		String token=loginService.loginUser(userRequestLogin);
		return ResponseEntity.ok(java.util.Map.of("token", token));
	}
	
}
