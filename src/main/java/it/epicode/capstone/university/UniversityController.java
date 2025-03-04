package it.epicode.capstone.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<University> createUniversity(@RequestBody UniversityDTO universityDTO) {

        return new ResponseEntity<>(universityService.createUniversity(universityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<University> updateUniversity(
            @PathVariable Long id, @RequestBody UniversityDTO universityDTO) {
        return ResponseEntity.ok(universityService.updateUniversity(id, universityDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return new ResponseEntity<>("University removed successfully", HttpStatus.NO_CONTENT);
    }

}
