package it.epicode.capstone.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Sector>> getAllSectors(Pageable pageable) {
        return ResponseEntity.ok(sectorService.getAllSectors(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Sector> createSector(@RequestBody SectorDTO sectorDTO) {
        return new ResponseEntity<>(sectorService.createSector(sectorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Sector> updateSector(@PathVariable Long id, @RequestBody SectorDTO sectorDTO) {
        return ResponseEntity.ok(sectorService.updateSector(id, sectorDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return new ResponseEntity<>("Sector removed successfully", HttpStatus.NO_CONTENT);
    }
}
