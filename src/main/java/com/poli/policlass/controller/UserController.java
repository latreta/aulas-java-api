package com.poli.policlass.controller;

import com.poli.policlass.event.RecursoCriadoEvent;
import com.poli.policlass.model.dto.UserDTO;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.model.form.AtualizarUsuarioForm;
import com.poli.policlass.repository.UserRepository;
import com.poli.policlass.service.SignupService;
import com.poli.policlass.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SignupService signupService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostMapping
	public ResponseEntity<User> cadastrar(@RequestBody User user, HttpServletResponse response) {
		User salvo = signupService.cadastrar(user);
		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AtualizarUsuarioForm> atualizar(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		// TODO: implementar a forma de validação dos dados para atualização
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> detalhar(@PathVariable Long id) {
		User salvo = userRepository.findById(id).get();
		return ResponseEntity.ok().body(salvo.generateDTO());
	}

	@GetMapping("/token/{token}")
	public ResponseEntity<?> recuperaDoToken(@PathVariable String token){
		User user = tokenService.recupera(token);
		return ResponseEntity.ok().body(user);
	}

}
