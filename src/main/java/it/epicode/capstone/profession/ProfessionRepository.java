package it.epicode.capstone.profession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession,Long> {

    Page<Profession> findBySectorName(String sectorName, Pageable pageable);

    boolean existsByName(String professionName);

    Optional<Profession> findByName(String professionName);
}
