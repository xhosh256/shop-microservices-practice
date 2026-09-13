package cephei.dev.auth_service.repository;


import cephei.dev.auth_service.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Integer> {
    Optional<Credentials> findByUsername(String username);
}
