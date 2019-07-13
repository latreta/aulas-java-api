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

import com.poli.policlass.model.entity.Cadeira;
import com.poli.policlass.repository.CadeiraRepository;

@RestController
@RequestMapping("/cadeiras")
public class CadeiraController {

	@Autowired
	private CadeiraRepository cadeiraRepository;

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Cadeira> cadastrar(@RequestBody Cadeira cadeira, UriComponentsBuilder uriBuilder) {
		cadeiraRepository.save(cadeira);
		URI uri = uriBuilder.path("/cadeiras/{id}").buildAndExpand(cadeira.getId().toString()).toUri();
		return ResponseEntity.created(uri).body(cadeira);
	}

	@GetMapping
	public List<Cadeira> listar() {
		return cadeiraRepository.findAll();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cadeiraRepository.deleteById(id);
	}
}
