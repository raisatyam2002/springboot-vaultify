package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.service.user.RegisterService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
   private  RegisterService registerService;
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
		registerService.createUser(userRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
	}
}
