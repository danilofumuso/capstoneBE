package it.epicode.capstone.active_users.professional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Optional<Professional> findByAppUserUsername(String username);

    void deleteByAppUserUsername(String username);

    Page<Professional> findByProfession_Sector_IdIn(Set<Long> sectorIds, Pageable pageable);
}

