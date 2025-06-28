package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<Map<String, String>> handleDuplicate(DuplicateUserException ex) {
		Map<String, String> res = new HashMap<>();
		res.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(res); // 409
	}

	@ExceptionHandler(WeakPasswordException.class)
	public ResponseEntity<Map<String, String>> handleWeakPassword(WeakPasswordException ex) {
		Map<String, String> res = new HashMap<>();
		res.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res); // 400
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleAny(Exception ex) {
		Map<String, String> res = new HashMap<>();
		res.put("error", "Unexpected error: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res); // 500
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException ex) {
		return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
				.body(Map.of("error", "File too large! Max size allowed is 10MB."));
	}
}
