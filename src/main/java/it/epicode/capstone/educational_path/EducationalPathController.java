package it.epicode.capstone.educational_path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educationalPaths")
public class EducationalPathController {

    @Autowired
    private EducationalPathService educationalPathService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL')")
    public ResponseEntity<EducationalPath> addEducationalPath(
            @AuthenticationPrincipal UserDetails professional,
            @RequestBody EducationalPathDTO educationalPathDTO) {

        return new ResponseEntity<>(educationalPathService.addEducationalPath(professional.getUsername(), educationalPathDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL')")
    public ResponseEntity<String> removeEducationalPath(@AuthenticationPrincipal UserDetails professional, @PathVariable Long id) {
        educationalPathService.removeEducationalPath(professional.getUsername(), id);
        return new ResponseEntity<>("Educational Path removed", HttpStatus.NO_CONTENT);
    }

}
