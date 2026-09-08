package cephei.dev.user_service.repository;

import cephei.dev.user_service.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
