package com.example.demo.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		return ResponseEntity.status(HttpStatus.CREATED).body("File received: " + file.getOriginalFilename());
	}

	@GetMapping("/test")
	public ResponseEntity<String> test(Principal principal) {
		System.out.println(principal.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(principal.getName());
	}
}
