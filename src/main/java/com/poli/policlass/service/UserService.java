package com.poli.policlass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User buscarPorID(Long id) {
		Optional<User> usuario = userRepository.findById(id);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}

	public List<User> buscarTodos() {
		return userRepository.findAll();
	}
}
