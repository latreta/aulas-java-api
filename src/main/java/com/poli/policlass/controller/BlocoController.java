package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Bloco;
import com.poli.policlass.repository.BlocoRepository;

@RestController
@RequestMapping("/blocos")
public class BlocoController {

	@Autowired
	private BlocoRepository blocoRepository;

	@GetMapping
	public ResponseEntity<List<Bloco>> listar() {
		return ResponseEntity.ok().body(blocoRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Bloco> cadastrar(@RequestBody Bloco bloco, UriComponentsBuilder uriBuilder) {
		blocoRepository.save(bloco);
		URI uri = uriBuilder.path("/blocos/{id}").buildAndExpand(bloco.getId().toString()).toUri();
		return ResponseEntity.created(uri).body(bloco);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		blocoRepository.deleteById(id);
	}
}
