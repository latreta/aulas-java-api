package com.poli.policlass.service;

import com.poli.policlass.model.entity.Bloco;
import com.poli.policlass.repository.BlocoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BlocoService {

	@Autowired
	private BlocoRepository blocoRepository;

	@Autowired
	private SalaService salaService;

	public List<Bloco> listarTodos() {
		return blocoRepository.findAll();
	}

	public boolean existeBloco(Long id) {
		return blocoRepository.findById(id).isPresent();
	}

	@Transactional
	public boolean removerBloco(Long id) {
		Bloco salvo = buscarPorID(id);
		if(salvo != null){
			salaService.removerPorBloco(id);
			blocoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean atualizarBloco(Long id, Bloco bloco){
		Bloco salvo = buscarPorID(id);
		if (salvo != null) {
			BeanUtils.copyProperties(bloco, salvo, "id");
			blocoRepository.save(salvo);
			return true;
		}
		return false;
	}

	public Bloco cadastrar(Bloco bloco) {
		return blocoRepository.save(bloco);
	}

	public Bloco buscarPorID(Long id) {
		Optional<Bloco> bloco = blocoRepository.findById(id);
		return bloco.orElse(null);
	}

}
