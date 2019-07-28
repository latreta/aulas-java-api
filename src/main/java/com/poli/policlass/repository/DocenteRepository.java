package com.poli.policlass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.Discente;

public interface DocenteRepository extends JpaRepository<Discente, Long> {

}
