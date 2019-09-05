package com.poli.policlass.service;

import com.poli.policlass.model.entity.Sala;
import com.poli.policlass.repository.SalaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private AulaService aulaService;

    public List<Sala> listarSalas(){
        return salaRepository.findAll();
    }

    @Transactional
    public void removerSala(Long id){
        aulaService.removerPorSala(id);
        salaRepository.deleteById(id);
    }

    @Transactional
    public void removerPorBloco(Long blocoId){
        List<Sala> salas = salaRepository.findByBloco_Id(blocoId);
        for(Sala sala : salas){
            removerSala(sala.getId());
        }
    }

    public Sala cadastrar(Sala sala){
        return salaRepository.save(sala);
    }

    public Sala buscarPorID(Long id){
        Optional<Sala> resultado = salaRepository.findById(id);
        return resultado.orElse(null);
    }

    @Transactional
    public boolean atualizarSala(Long id, Sala sala){
        Sala resultado = buscarPorID(id);
        if(resultado != null){
            BeanUtils.copyProperties(sala, resultado, "id");
            cadastrar(resultado);
            return true;
        }
        return false;
    }

}
