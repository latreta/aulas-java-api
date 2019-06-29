package com.poli.policlass.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.dto.UserDTO;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<UserDTO> cadastrar(@RequestBody UserDTO user, UriComponentsBuilder uriBuilder) {
		User saved = user.generateUserDTO();
		userRepository.save(saved);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(saved.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

}
