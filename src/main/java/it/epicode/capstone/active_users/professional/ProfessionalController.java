package it.epicode.capstone.active_users.professional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Page<Professional>> getAllProfessional(Pageable pageable){
        return ResponseEntity.ok(professionalService.getAllProfessionals(pageable));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL')")
    public ResponseEntity<Professional> updateProfessional(@AuthenticationPrincipal UserDetails professional, @RequestBody ProfessionalDTO professionalDTO) {
        return ResponseEntity.ok(professionalService.updateProfessional(professional.getUsername(), professionalDTO));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL')")
    public ResponseEntity<String> deleteProfessional(@AuthenticationPrincipal UserDetails professional) {
        professionalService.deleteProfessional(professional.getUsername());
        return new ResponseEntity<>("Professional removed successfully", HttpStatus.NO_CONTENT);
    }


}
