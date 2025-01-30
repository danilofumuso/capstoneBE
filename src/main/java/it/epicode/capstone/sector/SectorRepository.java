package it.epicode.capstone.sector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector,Long> {
    Optional<Sector> findByName(String name);

    boolean existsByName(String sectorName);

}
