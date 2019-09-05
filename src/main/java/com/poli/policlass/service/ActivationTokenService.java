package com.poli.policlass.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.poli.policlass.model.entity.ActivationToken;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.ActivationTokenRepository;
import com.poli.policlass.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ActivationTokenService {

	@Autowired
	private ActivationTokenRepository activationRepository;
	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void generateActivationToken(User usuario) {
		ActivationToken token = new ActivationToken();
		token.setToken(encoder.encode(usuario.getEmail()));
		token.setUser(usuario);
		activationRepository.save(token);
		sendEmail(usuario.getEmail(), token.getToken());
	}

	public boolean activateToken(Long id, String token){
		User salvo = userService.buscarPorID(id);
		salvo.setActivated(true);
		ActivationToken actToken = activationRepository.findByToken(token);
		if(salvo != null && actToken != null){
			if(encoder.matches(salvo.getEmail(), token)){
				userService.cadastrarUsuario(salvo);
				return true;
			}
		}
		return false;
	}

	private void sendEmail(String email, String token) {
		// Envia email formatado com token para ativacao
	}
}
