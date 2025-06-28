package com.example.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.repositry.UserRepository;
import com.example.demo.service.user.JwtService;
import com.example.demo.util.ErrorResponseUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtUtil; // Your custom JWT utility class

	@Autowired
	private UserRepository userRepository;;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			ErrorResponseUtil.sendError(response, 401, "Authorization header missing or malformed");
			return;
		}

		String token = authHeader.substring(7);

		if (token.isEmpty()) {
			ErrorResponseUtil.sendError(response, 401, "User not logged in. Token missing.");
			return;
		}

		if (!jwtUtil.validateToken(token)) {
			ErrorResponseUtil.sendError(response, 401, "Invalid or expired token. Please log in again.");
			return;
		}

		String userId = jwtUtil.extractId(token);

		if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, null);

			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);
		}

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath().startsWith("/api/user/");
	}
}
