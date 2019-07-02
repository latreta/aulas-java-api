package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Aula;
import com.poli.policlass.repository.AulaRepository;

@RestController
@RequestMapping("/aulas")
public class AulaController {

	@Autowired
	private AulaRepository aulaRepository;

	@GetMapping
	public ResponseEntity<List<Aula>> listar() {
		return ResponseEntity.ok().body(aulaRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Aula> cadastrar(@RequestBody Aula aula, UriComponentsBuilder uriBuilder) {
		aulaRepository.save(aula);
		URI uri = uriBuilder.path("aulas/{id}").buildAndExpand(aula.getId().toString()).toUri();
		return ResponseEntity.created(uri).body(aula);
	}

}
