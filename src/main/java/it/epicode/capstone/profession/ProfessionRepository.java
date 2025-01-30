package it.epicode.capstone.profession;

import it.epicode.capstone.sector.Sector;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession,Long> {

    Page<Profession> findBySectorName(String sectorName, Pageable pageable);

    boolean existsByName(String professionName);
}
