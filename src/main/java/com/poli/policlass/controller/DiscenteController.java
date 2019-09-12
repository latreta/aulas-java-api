package com.poli.policlass.controller;

import com.poli.policlass.model.entity.Discente;
import com.poli.policlass.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/discentes")
public class DiscenteController {

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
