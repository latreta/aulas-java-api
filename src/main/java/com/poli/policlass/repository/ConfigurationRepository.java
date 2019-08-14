package com.poli.policlass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {

}
