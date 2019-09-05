package com.poli.policlass.repository;

import com.poli.policlass.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
}
