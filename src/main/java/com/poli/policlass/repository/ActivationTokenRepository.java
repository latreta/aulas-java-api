package com.poli.policlass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.ActivationToken;

public interface ActivationTokenRepository extends JpaRepository<ActivationToken, Long> {
    ActivationToken findByToken(String token);
}
