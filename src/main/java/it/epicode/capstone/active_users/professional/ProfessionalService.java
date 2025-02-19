package it.epicode.capstone.active_users.professional;

import it.epicode.capstone.active_users.student.Student;
import it.epicode.capstone.active_users.student.StudentRepository;
import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.cloudinary.CloudinaryService;
import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.degree_courses.DegreeCourseRepository;
import it.epicode.capstone.educational_path.EducationalPath;
import it.epicode.capstone.educational_path.EducationalPathDTO;
import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.faculty.FacultyRepository;
import it.epicode.capstone.profession.Profession;
import it.epicode.capstone.profession.ProfessionDTO;
import it.epicode.capstone.profession.ProfessionRepository;
import it.epicode.capstone.sector.Sector;
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

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Page<Professional> getAllProfessionals(Pageable pageable) {
        return professionalRepository.findAll(pageable);
    }

    public Page<Professional> getAllProfessionalBySectors(String studentUsername, Pageable pageable) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Set<Long> sectorsId = student.getSectorsOfInterest()
                .stream()
                .map(Sector::getId)
                .collect(Collectors.toSet());

        return professionalRepository.findByProfession_Sector_IdIn(sectorsId, pageable);
    }

    public Page<Professional> getAllProfessionalByFilters(ProfessionDTO professionDTO, Pageable pageable) {
        if (professionDTO.getSectorId() != null && professionDTO.getId() != null) {
            return professionalRepository.findByProfession_Sector_IdAndProfession_Id(professionDTO.getSectorId(), professionDTO.getId(), pageable);
        } else if (professionDTO.getSectorId() != null) {
            return professionalRepository.findByProfession_Sector_Id(professionDTO.getSectorId(), pageable);
        } else if (professionDTO.getId() != null) {
            return professionalRepository.findByProfession_Id(professionDTO.getId(), pageable);
        } else {
            throw new IllegalArgumentException("At least one filter must be specified: sectorId or professionId.");
        }
    }

    public Optional<Professional> findById(Long id) {
        return professionalRepository.findById(id);
    }

    @Transactional
    public Professional updateProfessional(String professionalUsername, ProfessionalDTO professionalDTO) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        AppUser appUser = professional.getAppUser();
        if (professionalDTO.getName() != null && !professionalDTO.getName().isEmpty()) {
            appUser.setName(professionalDTO.getName());
        }

        if (professionalDTO.getSurname() != null && !professionalDTO.getSurname().isEmpty()) {
            appUser.setSurname(professionalDTO.getSurname());
        }

        if (professionalDTO.getDateOfBirth() != null) {
            appUser.setDateOfBirth(professionalDTO.getDateOfBirth());
        }

        if (professionalDTO.getUsername() != null && !professionalDTO.getUsername().isEmpty()) {
            appUser.setUsername(professionalDTO.getUsername());
        }

        if (professionalDTO.getEmail() != null && !professionalDTO.getEmail().isEmpty()) {
            appUser.setEmail(professionalDTO.getEmail());
        }

        if (professionalDTO.getPassword() != null && !professionalDTO.getPassword().isEmpty()) {
            appUser.setPassword(passwordEncoder.encode(professionalDTO.getPassword()));
        }

        return professionalRepository.save(professional);
    }

    public Professional updateEducationalPath(String professionalUsername, EducationalPathDTO educationalPathDTO) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (educationalPathDTO.getUniversityId() != null &&
                educationalPathDTO.getFacultyId() != null &&
                educationalPathDTO.getDegreeCourseId() != null) {
            University university = universityRepository.findById(educationalPathDTO.getUniversityId())
                    .orElseThrow(() -> new EntityNotFoundException("University not found"));

            Faculty faculty = facultyRepository.findById(educationalPathDTO.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

            DegreeCourse degreeCourse = degreeCourseRepository.findById(educationalPathDTO.getDegreeCourseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course not found"));

            EducationalPath educationalPath = new EducationalPath(professional, university, faculty, degreeCourse);
            if (!professional.getEducationalPaths().contains(educationalPath)) {
                professional.addEducationalPath(educationalPath);
            }

        }
        return professionalRepository.save(professional);
    }

    public Professional updateProfession(String professionalUsername, ProfessionDTO professionDTO) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (professionDTO.getId() != null) {
            Profession profession = professionRepository.findById(professionDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Profession not found"));
            professional.setProfession(profession);
        }
        return professionalRepository.save(professional);
    }

    public Professional updateProfilePicture(String professionalUsername, MultipartFile profilePicture) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        AppUser appUser = professional.getAppUser();

        if (profilePicture != null && !profilePicture.isEmpty()) {
            appUser.setProfilePicture(cloudinaryService.uploader(profilePicture, "profilePictures", "image").get("url").toString());
        } else {
            appUser.setProfilePicture(null);
        }

        return professionalRepository.save(professional);
    }


    public Professional updateWrittenStory(String professionalUsername, String writtenStory) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (writtenStory != null && !writtenStory.isEmpty()) {
            professional.setWrittenStory(writtenStory);
        } else {
            professional.setWrittenStory(null);
        }

        return professionalRepository.save(professional);
    }

    public Professional updateVideoStory(String professionalUsername, MultipartFile videoStory) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (videoStory != null && !videoStory.isEmpty()) {
            professional.setVideoStory(cloudinaryService.uploader(videoStory, "videoStories", "video").get("url").toString());
        } else {
            professional.setVideoStory(null);
        }

        return professionalRepository.save(professional);
    }

    public Professional updateCurriculumVitae(String professionalUsername, MultipartFile curriculumVitae) {
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        if (curriculumVitae != null && !curriculumVitae.isEmpty()) {
            professional.setCurriculumVitae(cloudinaryService.uploader(curriculumVitae, "curricula", "raw").get("url").toString());
        } else {
            professional.setCurriculumVitae(null);
        }

        return professionalRepository.save(professional);
    }

    @Transactional
    public void deleteProfessional(String professionalUsername) {
        professionalRepository.deleteByAppUserUsername(professionalUsername);
    }


}
