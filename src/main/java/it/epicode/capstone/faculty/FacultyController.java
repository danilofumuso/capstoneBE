package it.epicode.capstone.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/faculties")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Faculty>> getAllFaculties(Pageable pageable) {
        return ResponseEntity.ok(facultyService.getAllFaculties(pageable));
    }

    @GetMapping("/byUniversity")
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Faculty>> getAllFacultiesByUniversity(@RequestParam String universityName, Pageable pageable) {
        return ResponseEntity.ok(facultyService.getAllFacultiesByUniversity(universityName, pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> createFaculty(@RequestBody FacultyDTO facultyDTO) {
        return new ResponseEntity<>(facultyService.createFaculty(facultyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody FacultyDTO facultyDTO) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, facultyDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>("Faculty removed successfully", HttpStatus.NO_CONTENT);
    }
}
