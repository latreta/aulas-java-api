package com.poli.policlass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poli.policlass.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String email);
}
