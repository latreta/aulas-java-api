package com.poli.policlass.config.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			return user.get();
		}

		throw new UsernameNotFoundException("Dados incorretos");
	}

}
