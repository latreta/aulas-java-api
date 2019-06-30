package com.poli.policlass.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.poli.policlass.model.entity.ActivationToken;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.ActivationTokenRepository;
import com.poli.policlass.repository.UserRepository;

public class ActivationTokenService {

	@Autowired
	private ActivationTokenRepository activationRepository;
	@Autowired
	private UserRepository userRepository;

	public void generateActivationToken(Long id) {
		Optional<User> usuario = userRepository.findById(id);
		if (usuario.isPresent()) {
			User salvo = usuario.get();
			ActivationToken token = new ActivationToken();
			token.setToken("Teste");
			token.setUser(salvo);
			activationRepository.save(token);
			sendEmail(salvo.getEmail(), token.getToken());
		}

	}

	private void sendEmail(String email, String token) {
		// Envia email formatado com token para ativacao
	}
}
