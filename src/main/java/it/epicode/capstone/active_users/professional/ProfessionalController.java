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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Professional>> getAllProfessional(Pageable pageable) {
        return ResponseEntity.ok(professionalService.getAllProfessionals(pageable));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Professional> updateProfessional(@AuthenticationPrincipal UserDetails professional, @RequestBody ProfessionalDTO professionalDTO) {
        return ResponseEntity.ok(professionalService.updateProfessional(professional.getUsername(), professionalDTO));
    }

    @PatchMapping("/writtenStory")
    public ResponseEntity<Professional> updateWrittenStory(
            @AuthenticationPrincipal UserDetails professional,
            @RequestBody ProfessionalWrittenStoryDTO professionalWrittenStoryDTO) {

        return ResponseEntity.ok(professionalService.updateWrittenStory(professional.getUsername(), professionalWrittenStoryDTO.getWrittenStory()));
    }

    @PatchMapping(path = "/videoStory", consumes = {"multipart/form-data"})
    public ResponseEntity<Professional> updateVideoStory(
            @AuthenticationPrincipal UserDetails professional,
            @RequestParam(value = "videoStory", required = false) MultipartFile videoStory) {

        return ResponseEntity.ok(professionalService.updateVideoStory(professional.getUsername(), videoStory));
    }

    @PatchMapping(path = "/curriculumVitae", consumes = {"multipart/form-data"})
    public ResponseEntity<Professional> updateCurriculumVitae(
            @AuthenticationPrincipal UserDetails professional,
            @RequestParam(value = "curriculumVitae", required = false) MultipartFile curriculumVitae) {

        return ResponseEntity.ok(professionalService.updateCurriculumVitae(professional.getUsername(), curriculumVitae));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProfessional(@AuthenticationPrincipal UserDetails professional) {
        professionalService.deleteProfessional(professional.getUsername());
        return new ResponseEntity<>("Professional removed successfully", HttpStatus.NO_CONTENT);
    }

}
