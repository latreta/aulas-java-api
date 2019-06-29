package com.poli.policlass.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.poli.policlass.config.service.TokenService;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.UserRepository;

public class TokenAuthFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;

	public TokenAuthFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = tokenService.retrieveToken(request);

		boolean isValid = tokenService.isValidToken(token);

		if (isValid) {
			authenticateClient(token);
		}

		filterChain.doFilter(request, response);

	}

	private void authenticateClient(String token) {
		Long userID = tokenService.getUserID(token);
		User user = userRepository.findById(userID).get();
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
