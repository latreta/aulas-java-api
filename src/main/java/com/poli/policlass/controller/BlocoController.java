package com.poli.policlass.controller;

import com.poli.policlass.event.RecursoCriadoEvent;
import com.poli.policlass.model.entity.Bloco;
import com.poli.policlass.service.BlocoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
