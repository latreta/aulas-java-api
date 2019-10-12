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
import java.util.Optional;

@RestController
@RequestMapping("/discentes")
public class DiscenteController {

	@Autowired
	private DocenteRepository docenteRepository;

	@GetMapping
	public List<Discente> listar() {
		return docenteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Discente> detalhar(@PathVariable Long id){
		Optional<Discente> resultado = docenteRepository.findById(id);
		if(resultado.isPresent()){
			Discente discente = resultado.get();
			return ResponseEntity.ok(discente);
		}

		return ResponseEntity.badRequest().build();
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
