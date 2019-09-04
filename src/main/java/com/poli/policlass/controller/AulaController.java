package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.poli.policlass.event.RecursoCriadoEvent;
import com.poli.policlass.service.AulaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Aula;
import com.poli.policlass.repository.AulaRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/aulas")
public class AulaController {

	@Autowired
	private AulaService aulaService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping
	public ResponseEntity<List<Aula>> listar() {
		return ResponseEntity.ok(aulaService.listarAulas());
	}

	@PostMapping
	public ResponseEntity<Aula> cadastrar(@RequestBody @Valid Aula aula, HttpServletResponse response) {
		Aula salva = aulaService.cadastrar(aula);
		if(salva == null){
			return ResponseEntity.badRequest().build();
		}
		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, salva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aula> atualizar(@PathVariable Long id, @RequestBody Aula aula) {
		boolean status = aulaService.atualizar(id, aula);
		return status ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id){
		aulaService.remover(id);
	}

}
