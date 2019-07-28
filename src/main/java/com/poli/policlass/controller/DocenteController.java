package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Discente;
import com.poli.policlass.repository.DocenteRepository;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

	@Autowired
	private DocenteRepository docenteRepository;

	@GetMapping
	public List<Discente> listar() {
		return docenteRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Discente> cadastrar(@RequestBody Discente docente, UriComponentsBuilder uriBuilder) {
		docenteRepository.save(docente);
		URI uri = uriBuilder.path("/docentes/{id}").buildAndExpand(docente.getId()).toUri();
		return ResponseEntity.created(uri).body(docente);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		docenteRepository.deleteById(id);
	}
}
