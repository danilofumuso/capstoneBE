package it.epicode.capstone.profession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professions")
public class ProfessionController {

    @Autowired
    private ProfessionService professionService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Profession>> getAllProfessions(Pageable pageable) {
        return ResponseEntity.ok(professionService.getAllProfessions(pageable));
    }

    @GetMapping("/bySector")
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Profession>> getAllProfessionsBySector(@RequestParam String sectorName, Pageable pageable) {
        return ResponseEntity.ok(professionService.getAllProfessionsBySector(sectorName, pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Profession> createProfession(@RequestBody ProfessionDTO professionDTO) {
        Profession profession = professionService.createProfession(professionDTO);
        return ResponseEntity.ok(profession);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Profession> updateProfession(
            @PathVariable Long id,
            @RequestBody ProfessionDTO professionDTO) {
        Profession updatedProfession = professionService.updateProfession(id, professionDTO);
        return ResponseEntity.ok(updatedProfession);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProfession(@PathVariable Long id) {
        professionService.deleteProfession(id);
        return new ResponseEntity<>("Profession removed successfully", HttpStatus.NO_CONTENT);
    }

}
