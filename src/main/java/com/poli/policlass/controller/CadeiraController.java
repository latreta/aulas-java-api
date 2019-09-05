package com.poli.policlass.controller;

import com.poli.policlass.model.entity.Cadeira;
import com.poli.policlass.repository.CadeiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
