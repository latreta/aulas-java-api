package com.poli.policlass.controller;

import com.poli.policlass.event.RecursoCriadoEvent;
import com.poli.policlass.model.entity.Disciplina;
import com.poli.policlass.repository.CadeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private CadeiraRepository cadeiraRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina, HttpServletResponse response) {
        cadeiraRepository.save(disciplina);
        eventPublisher.publishEvent(new RecursoCriadoEvent(this, response, disciplina.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }

    @GetMapping
    public List<Disciplina> listar() {
        return cadeiraRepository.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        cadeiraRepository.deleteById(id);
    }
}
