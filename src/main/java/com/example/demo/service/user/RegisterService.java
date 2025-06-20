package com.example.demo.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.exception.WeakPasswordException;
import com.example.demo.model.User;
import com.example.demo.repositry.UserRepository;


@Service
public class RegisterService {

	private final UserRepository userRepository;
	public RegisterService( UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	public void  createUser(UserRequest userRequest) {
		
		if(userRepository.existsByUsername(userRequest.getUsername())) {
			throw new DuplicateUserException("Username already exist");
		}
		if(userRequest.getPassword().length()<8) {
			throw new WeakPasswordException("Password should be greater than equals to 8 character");
		}
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user=new User();
		user.setName(userRequest.getName());
		user.setUsername(userRequest.getUsername());
		user.setPassword(encoder.encode(userRequest.getPassword()));
		try {
		
		userRepository.save(user);
		}
		catch(Exception e) {
			throw new RuntimeException("error while saving user "+ e.getMessage());
		}
	}
}
