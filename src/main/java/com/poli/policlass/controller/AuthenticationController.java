package com.poli.policlass.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poli.policlass.config.service.TokenService;
import com.poli.policlass.model.dto.TokenDTO;
import com.poli.policlass.model.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok().body(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

}
