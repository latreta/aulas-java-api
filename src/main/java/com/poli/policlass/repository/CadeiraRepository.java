package com.poli.policlass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.Disciplina;

public interface CadeiraRepository extends JpaRepository<Disciplina, Long> {

}
