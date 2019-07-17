package com.poli.policlass.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.Bloco;
import com.poli.policlass.repository.BlocoRepository;
import com.poli.policlass.repository.SalaRepository;

@Service
public class BlocoService {

	@Autowired
	private BlocoRepository blocoRepository;

	@Autowired
	private SalaRepository salaRepository;

	public List<Bloco> listarTodos() {
		return blocoRepository.findAll();
	}

	public boolean existeBloco(Long id) {
		return blocoRepository.findById(id).isPresent();
	}

	/*
	 * TODO: Implementar a lógica para remoção de todos os elementos com o mesmo
	 * bloco para evitar erros
	 */
	@Transactional
	public void removerBloco(Bloco bloco) {
		salaRepository.deleteByBloco_Id(bloco.getId());
		blocoRepository.deleteById(bloco.getId());
	}

	public void cadastrar(Bloco bloco) {
		blocoRepository.save(bloco);
	}

	public Bloco buscarPorID(Long id) {
		Optional<Bloco> bloco = blocoRepository.findById(id);
		if (bloco.isPresent()) {
			return bloco.get();
		} else {
			return null;
		}
	}

}
