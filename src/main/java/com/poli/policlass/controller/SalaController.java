package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.poli.policlass.event.RecursoCriadoEvent;
import com.poli.policlass.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Sala;
import com.poli.policlass.repository.SalaRepository;
import com.poli.policlass.service.BlocoService;

@RestController
@RequestMapping("/salas")
public class SalaController {

	@Autowired
	private SalaService salaService;


	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping
	public ResponseEntity<List<Sala>> listar() {
		return ResponseEntity.ok(salaService.listarSalas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sala> detalhar(@PathVariable Long id){
		return ResponseEntity.ok(salaService.buscarPorID(id));
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody @Valid Sala sala){
		salaService.atualizarSala(id, sala);
	}

	@PostMapping
	public ResponseEntity<Sala> cadastrar(@RequestBody @Valid Sala sala, HttpServletResponse response) {
		Sala salvo = salaService.cadastrar(sala);
		eventPublisher.publishEvent(new RecursoCriadoEvent(this,response,salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(sala);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		salaService.removerSala(id);
	}

}
