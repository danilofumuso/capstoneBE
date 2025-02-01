package it.epicode.capstone.sector;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public Page<Sector> getAllSectors(Pageable pageable) {
        return sectorRepository.findAll(pageable);
    }

    public Sector createSector(SectorDTO sectorDTO) {
        if (sectorRepository.existsByName(sectorDTO.getName())) {
            throw new EntityExistsException("Sector already exists");
        }
        Sector sector = new Sector();

        sector.setName(sectorDTO.getName());
        return sectorRepository.save(sector);
    }

    public Sector updateSector(Long sectorId,SectorDTO sectorDTO) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new EntityNotFoundException("Sector not found"));

        sector.setName(sectorDTO.getName());
        return sectorRepository.save(sector);
    }

    public void deleteSector(Long sectorId) {

        sectorRepository.deleteById(sectorId);
    }

}
