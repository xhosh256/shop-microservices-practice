package cephei.dev.user_service.repository;

import aj.org.objectweb.asm.commons.Remapper;
import cephei.dev.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
