package com.example.demo.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequestLogin;
import com.example.demo.model.User;
import com.example.demo.repositry.UserRepository;

@Service
public class LoginService {
	private final UserRepository userRepository;
	private final JwtService jwtService;
	public LoginService(UserRepository userRepositry,JwtService jwtService) {
		this.userRepository=userRepositry;
		this.jwtService=jwtService;
	}
	public String loginUser(UserRequestLogin userRequestLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			User user=userRepository.findByUsername(userRequestLogin.getUsername());
			if(user==null) {
				 throw new RuntimeException("User does not exist");
			}
		boolean isPasswordMatch = encoder.matches(userRequestLogin.getPassword(), user.getPassword());
		if(!isPasswordMatch) {
			throw new RuntimeException("Password is wrong, Please enter the current password");
		}
		String token=jwtService.generateToken(user.getUsername());
		return token;
			
			
		
}
	}

