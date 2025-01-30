package it.epicode.capstone.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Sector>> getAllSectors(Pageable pageable) {
        return ResponseEntity.ok(sectorService.getAllSectors(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Sector> createSector(@RequestBody String sectorName) {
        return new ResponseEntity<>(sectorService.createSector(sectorName), HttpStatus.CREATED);
    }

    @PutMapping("/{sectorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Sector> updateSector(@PathVariable Long sectorId, @RequestBody String newSectorName) {
        return ResponseEntity.ok(sectorService.updateSector(sectorId, newSectorName));
    }

    @DeleteMapping("/{sectorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteSector(@PathVariable Long sectorId) {
        sectorService.deleteSector(sectorId);
        return new ResponseEntity<>("Sector removed successfully", HttpStatus.NO_CONTENT);
    }
}
