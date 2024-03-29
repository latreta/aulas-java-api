package com.poli.policlass.repository;

import com.poli.policlass.model.entity.AuthenticableUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository<T extends AuthenticableUser> extends JpaRepository<T, Long> {
	Optional<T> findByEmail(String email);
}
