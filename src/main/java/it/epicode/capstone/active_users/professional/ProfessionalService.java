package it.epicode.capstone.active_users.professional;

import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.cloudinary.CloudinaryService;
import it.epicode.capstone.profession.Profession;
import it.epicode.capstone.profession.ProfessionRepository;
import it.epicode.capstone.university.University;
import it.epicode.capstone.university.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Page<Professional> getAllProfessionals(Pageable pageable) {
        return professionalRepository.findAll(pageable);
    }

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

        if (professionalDTO.getUniversitiesNames() != null) {
            Set<String> universities = new HashSet<>(professionalDTO.getUniversitiesNames());

            professional.setUniversities(universities);
        }

        if (professionalDTO.getFacultiesNames() != null) {
            Set<String> faculties = new HashSet<>(professionalDTO.getFacultiesNames());

            professional.setFaculties(faculties);
        }

        if (professionalDTO.getDegreeCoursesNames() != null) {
            Set<String> degreeCourses = new HashSet<>(professionalDTO.getDegreeCoursesNames());

            professional.setDegreeCourses(degreeCourses);
        }

        if (professionalDTO.getProfessionName() != null) {
            Profession profession = professionRepository.findByName(professionalDTO.getProfessionName())
                    .orElseThrow(() -> new EntityNotFoundException("Profession not found"));

            professional.setProfession(profession);
        }

        return professionalRepository.save(professional);
    }

    public Professional updateWrittenStory(String professionalUsername, String writtenStory) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));
        professional.setWrittenStory(writtenStory);

        return professionalRepository.save(professional);
    }

    public Professional updateVideoStory(String professionalUsername, MultipartFile videoStory) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (videoStory != null && !videoStory.isEmpty()) {
            professional.setVideoStory(cloudinaryService.uploader(videoStory, "videoStories").get("url").toString());
        }

        return professionalRepository.save(professional);
    }

    public Professional updateCurriculumVitae(String professionalUsername, MultipartFile curriculumVitae) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (curriculumVitae != null && !curriculumVitae.isEmpty()) {
            professional.setCurriculumVitae(cloudinaryService.uploader(curriculumVitae, "curricula").get("url").toString());
        }

        return professionalRepository.save(professional);
    }

    @Transactional
    public void deleteProfessional(String professionalUsername) {
        professionalRepository.deleteByAppUserUsername(professionalUsername);
    }
}
