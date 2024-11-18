package org.andarworld.authenticationservice.persistence.repository;

import org.andarworld.authenticationservice.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
