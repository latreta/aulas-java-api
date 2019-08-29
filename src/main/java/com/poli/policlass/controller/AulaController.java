package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		return ResponseEntity.ok(aulaRepository.findAll());
	}

	@PostMapping
	public ResponseEntity<Aula> cadastrar(@RequestBody Aula aula, UriComponentsBuilder uriBuilder) {
		aulaRepository.save(aula);
		URI uri = uriBuilder.path("aulas/{id}").buildAndExpand(aula.getId().toString()).toUri();
		return ResponseEntity.created(uri).body(aula);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aula> atualizar(@PathVariable Long id, @RequestBody Aula aula) {
		Optional<Aula> salvo = aulaRepository.findById(id);

		if (salvo.isPresent()) {
			Aula armazenado = salvo.get();
			BeanUtils.copyProperties(aula, armazenado, "id");
			aulaRepository.save(armazenado);
		}
		return ResponseEntity.noContent().build();
	}

}
