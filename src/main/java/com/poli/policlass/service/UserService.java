package com.poli.policlass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.User;
import com.poli.policlass.repository.UserRepository;

@Service(value = "userService")
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public User buscarPorID(Long id) {
		Optional<User> usuario = userRepository.findById(id);
		return usuario.orElse(null);
	}

	public User cadastrarUsuario(User usuario){
		return userRepository.save(usuario);
	}

	public List<User> buscarTodos() {
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userSalvo = userRepository.findByEmail(email);
		if(userSalvo.isPresent()){
			return userSalvo.get();
		}
		else{
			throw new UsernameNotFoundException("Dados incorretos.");
		}
	}
}
