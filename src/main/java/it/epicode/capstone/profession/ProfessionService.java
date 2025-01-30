package it.epicode.capstone.profession;

import it.epicode.capstone.sector.Sector;
import it.epicode.capstone.sector.SectorRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private SectorRepository sectorRepository;

    public Page<Profession> getAllProfessions(Pageable pageable) {
        return professionRepository.findAll(pageable);
    }

    public Page<Profession> getAllProfessionsBySector(String sectorName, Pageable pageable) {
        return professionRepository.findBySectorName(sectorName, pageable);
    }

    public Profession createProfession(ProfessionDTO professionDTO) {
        if (professionRepository.existsByName(professionDTO.getName())) {
            throw new EntityExistsException("Profession already exists");
        }

        Sector sector = sectorRepository.findByName(professionDTO.getSectorName())
                .orElseThrow(() -> new EntityNotFoundException("Sector not found"));

        Profession profession = new Profession();
        profession.setName(professionDTO.getName());
        profession.setSector(sector);

        return professionRepository.save(profession);
    }

    public Profession updateProfession(Long professionId, ProfessionDTO professionDTO) {
        Profession profession = professionRepository.findById(professionId)
                .orElseThrow(() -> new EntityNotFoundException("Profession not found"));

        profession.setName(professionDTO.getName());
        Sector sector = sectorRepository.findByName(professionDTO.getSectorName())
                .orElseThrow(()-> new EntityNotFoundException("Sector not found"));

        profession.setSector(sector);

        return professionRepository.save(profession);
    }

    public void deleteProfession(Long professionId) {
        Profession profession = professionRepository.findById(professionId)
                .orElseThrow(() -> new EntityNotFoundException("Profession not found"));

        professionRepository.delete(profession);
    }

}




