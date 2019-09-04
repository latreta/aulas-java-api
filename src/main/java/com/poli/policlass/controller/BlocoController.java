package com.poli.policlass.controller;

import java.net.URI;
import java.util.List;

import com.poli.policlass.event.RecursoCriadoEvent;
import javafx.application.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poli.policlass.model.entity.Bloco;
import com.poli.policlass.service.BlocoService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/blocos")
public class BlocoController {

	@Autowired
	private BlocoService blocoService;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@GetMapping
	public ResponseEntity<List<Bloco>> listar() {
		return ResponseEntity.ok().body(blocoService.listarTodos());
	}

	@PostMapping
	public ResponseEntity<Bloco> cadastrar(@RequestBody Bloco bloco, HttpServletResponse response) {
		Bloco salvo = blocoService.cadastrar(bloco);
		eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, salvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bloco);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bloco> atualizar(@PathVariable Long id, @RequestBody Bloco bloco) {
		boolean status = blocoService.atualizarBloco(id, bloco);
		return status ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		boolean status = blocoService.removerBloco(id);
	}
}
