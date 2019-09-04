package com.poli.policlass.controller;

import java.net.URI;
import java.util.Optional;

import com.poli.policlass.event.RecursoCriadoEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.dto.UserDTO;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.model.form.CadastroForm;
import com.poli.policlass.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostMapping
	public ResponseEntity<UserDTO> cadastrar(@RequestBody CadastroForm form, HttpServletResponse response) {
		User salvo = form.convert();
		userRepository.save(salvo);
		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo.generateDTO());
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> atualizar(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		Optional<User> resultado = userRepository.findById(id);

		if (resultado.isPresent()) {
			User user = userDTO.convert();
			User salvo = resultado.get();
			BeanUtils.copyProperties(user, salvo, "id", "email");
			userRepository.save(salvo);
			return ResponseEntity.ok().body(salvo.generateDTO());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> detalhar(@PathVariable Long id) {
		User salvo = userRepository.findById(id).get();
		return ResponseEntity.ok().body(salvo.generateDTO());
	}

}
