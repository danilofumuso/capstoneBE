package it.epicode.capstone.active_users.professional;

import it.epicode.capstone.educational_path.EducationalPathDTO;
import it.epicode.capstone.profession.ProfessionDTO;
import jakarta.persistence.EntityNotFoundException;
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

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Professional> getProfessionalById(@PathVariable Long id) {
        Professional professional = professionalService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));
        return ResponseEntity.ok(professional);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_PROFESSIONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Professional> updateProfessional(@AuthenticationPrincipal UserDetails professional, @RequestBody ProfessionalDTO professionalDTO) {
        return ResponseEntity.ok(professionalService.updateProfessional(professional.getUsername(), professionalDTO));
    }

    @PatchMapping("/educationalPath")
    public ResponseEntity<Professional> updateEducationalPath(@AuthenticationPrincipal UserDetails professional, @RequestBody EducationalPathDTO educationalPathDTO) {
        return ResponseEntity.ok(professionalService.updateEducationalPath(professional.getUsername(), educationalPathDTO));
    }

    @PatchMapping("/profession")
    public ResponseEntity<Professional> updateProfession(UserDetails professional,
                                                         @RequestBody ProfessionDTO professionDTO) {

        return ResponseEntity.ok(professionalService.updateProfession(professional.getUsername(), professionDTO));
    }

    @PatchMapping(path = "/profilePicture", consumes = {"multipart/form-data"})
    public ResponseEntity<Professional> updateProfilePicture(
            @AuthenticationPrincipal UserDetails professional,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {

        return ResponseEntity.ok(professionalService.updateProfilePicture(professional.getUsername(), profilePicture));
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
