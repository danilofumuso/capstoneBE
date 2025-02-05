package it.epicode.capstone.sector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
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
