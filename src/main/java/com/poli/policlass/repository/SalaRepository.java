package com.poli.policlass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	List<Sala> findByBloco_Id(Long id);

	void deleteByBloco_Id(Long id);
}
