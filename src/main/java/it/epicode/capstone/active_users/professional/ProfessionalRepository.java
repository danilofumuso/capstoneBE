package it.epicode.capstone.active_users.professional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByAppUserUsername(String username);

    void deleteByAppUserUsername(String username);
}

