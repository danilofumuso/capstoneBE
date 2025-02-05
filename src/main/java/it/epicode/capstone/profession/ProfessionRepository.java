package it.epicode.capstone.profession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    List<Profession> findBySector_Id(Long sectorId);

    boolean existsByName(String professionName);

    Optional<Profession> findByName(String professionName);
}
