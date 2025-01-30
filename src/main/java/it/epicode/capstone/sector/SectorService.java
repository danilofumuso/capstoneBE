package it.epicode.capstone.sector;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public Page<Sector> getAllSectors(Pageable pageable) {
        return sectorRepository.findAll(pageable);
    }

    public Sector createSector(String sectorName) {
        if (sectorRepository.existsByName(sectorName)) {
            throw new EntityExistsException("Sector already exists");
        }
        Sector sector = new Sector();

        sector.setName(sectorName);
        return sectorRepository.save(sector);
    }

    public Sector updateSector(Long sectorId,String newSectorName) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new EntityNotFoundException("Sector not found"));

        sector.setName(newSectorName);
        return sectorRepository.save(sector);
    }

    public void deleteSector(Long sectorId) {

        sectorRepository.deleteById(sectorId);
    }

}
