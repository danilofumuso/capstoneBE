package it.epicode.capstone.active_users.professional;

import it.epicode.capstone.auth.AppUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Professional updateProfessional(String professionalUsername, ProfessionalDTO professionalDTO) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        AppUser appUser = professional.getAppUser();
        if (professionalDTO.getName() != null) {
            appUser.setName(professionalDTO.getName());
        }

        if (professionalDTO.getSurname() != null) {
            appUser.setSurname(professionalDTO.getSurname());
        }

        if (professionalDTO.getDateOfBirth() != null) {
            appUser.setDateOfBirth(professionalDTO.getDateOfBirth());
        }

        if (professionalDTO.getUsername() != null) {
            appUser.setUsername(professionalDTO.getUsername());
        }

        if (professionalDTO.getEmail() != null) {
            appUser.setEmail(professionalDTO.getEmail());
        }

        if (professionalDTO.getPassword() != null) {
            appUser.setPassword(passwordEncoder.encode(professionalDTO.getPassword()));
        }

        if (professionalDTO.getUniversities() != null) {
            professional.getUniversities().addAll(professionalDTO.getUniversities());
        }

        if (professionalDTO.getFaculties() != null) {
            professional.getFaculties().addAll(professionalDTO.getFaculties());
        }

        if (professionalDTO.getCourses() != null) {
            professional.getCourses().addAll(professionalDTO.getCourses());
        }

        if (professionalDTO.getAcademicTitles() != null) {
            professional.getAcademicTitles().addAll(professionalDTO.getAcademicTitles());
        }

        if (professionalDTO.getOccupation() != null) {
            professional.setOccupation(professionalDTO.getOccupation());
        }

        if (professionalDTO.getOccupationArea() != null) {
            professional.setOccupationArea(professionalDTO.getOccupationArea());
        }

        if (professionalDTO.getAcademicCareerPath() != null) {
            professional.setAcademicCareerPath(professionalDTO.getAcademicCareerPath());
        }

        if (professionalDTO.getAcademicCareerPathVideo() != null) {
            professional.setAcademicCareerPathVideo(professionalDTO.getAcademicCareerPathVideo());
        }

        if (professionalDTO.getCurriculumVitae() != null) {
            professional.setCurriculumVitae(professionalDTO.getCurriculumVitae());
        }

        return professionalRepository.save(professional);
    }

    @Transactional
    public void deleteProfessional(String professionalUsername) {
        professionalRepository.deleteByAppUserUsername(professionalUsername);
    }
}
