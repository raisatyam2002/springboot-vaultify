package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.User;
import com.example.demo.service.file.UploadFileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	@Autowired
	private UploadFileService uploadFileService;

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
			@AuthenticationPrincipal User user) {
		uploadFileService.uploadFile(file, user);
		return ResponseEntity.status(HttpStatus.CREATED).body("File uploades successfully");
	}

	@GetMapping("/test")
	public ResponseEntity<String> test(Principal principal) {
		System.out.println(principal.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(principal.getName());
	}
}
