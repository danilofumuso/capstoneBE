package it.epicode.capstone.degree_courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/degreeCourses")
public class DegreeCourseController {

    @Autowired
    private DegreeCourseService degreeCourseService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<DegreeCourse>> getAllDegreeCourses(Pageable pageable) {
        return ResponseEntity.ok(degreeCourseService.getAllDegreeCourses(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DegreeCourse> createDegreeCourse(@RequestBody DegreeCourseDTO degreeCourseDTO) {
        return new ResponseEntity<>(degreeCourseService.createDegreeCourse(degreeCourseDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DegreeCourse> updateDegreeCourse(@PathVariable Long id, @RequestBody DegreeCourseDTO degreeCourseDTO) {
        return ResponseEntity.ok(degreeCourseService.updateDegreeCourse(id, degreeCourseDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteDegreeCourse(@PathVariable Long id) {
        degreeCourseService.deleteDegreeCourse(id);
        return new ResponseEntity<>("DegreeCourse removed successfully", HttpStatus.NO_CONTENT);
    }

}
