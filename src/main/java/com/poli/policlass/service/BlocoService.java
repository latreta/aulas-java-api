package com.poli.policlass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poli.policlass.repository.BlocoRepository;

@Service
public class BlocoService {

	@Autowired
	private BlocoRepository blocoRepository;

	public boolean existeBloco(Long id) {
		return blocoRepository.findById(id).isPresent();
	}

	/*
	 * TODO: Implementar a lógica para remoção de todos os elementos com o mesmo
	 * bloco para evitar erros
	 */
	public boolean removerBloco(Long bloco) {
		return false;
	}

}
