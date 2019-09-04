package com.poli.policlass.service;

import com.poli.policlass.model.entity.Aula;
import com.poli.policlass.repository.AulaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public List<Aula> listarAulas(){
        return aulaRepository.findAll();
    }

    public Aula buscarPorID(Long id){
        Optional<Aula> resultado = aulaRepository.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        return null;
    }

    public Aula cadastrar(Aula aula){
        return aulaRepository.save(aula);
    }

    @Transactional
    public boolean atualizar(Long id, Aula aula){
        Aula salvo = buscarPorID(id);
        if (salvo != null) {
            BeanUtils.copyProperties(aula, salvo, "id");
            cadastrar(salvo);
            return true;
        }
        return false;
    }

    @Transactional
    public void remover(Long id){
        Aula aula = buscarPorID(id);
        if(aula != null){
            aulaRepository.delete(aula);
        }
    }

    @Transactional
    public void removerPorSala(Long salaId){
        aulaRepository.deleteBySala_Id(salaId);
    }
}
