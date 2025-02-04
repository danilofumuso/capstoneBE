package it.epicode.capstone.educational_path;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.professional.ProfessionalRepository;
import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.degree_courses.DegreeCourseRepository;
import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.faculty.FacultyRepository;
import it.epicode.capstone.university.University;
import it.epicode.capstone.university.UniversityRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EducationalPathService {

    @Autowired
    private EducationalPathRepository educationalPathRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;

    @Transactional
    public EducationalPath addEducationalPath(String professionalUsername, EducationalPathDTO educationalPathDTO) {
        if (educationalPathRepository.existsByProfessionalAppUserUsernameAndDegreeCourseId(professionalUsername, educationalPathDTO.getDegreeCourseId())) {
            throw new EntityExistsException("This Educational Path already exists");
        }
        Professional professional = professionalRepository.findByAppUserUsername(professionalUsername)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        University university = universityRepository.findById(educationalPathDTO.getUniversityId())
                .orElseThrow(() -> new EntityNotFoundException("University not found"));

        Faculty faculty = facultyRepository.findById(educationalPathDTO.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        DegreeCourse degreeCourse = degreeCourseRepository.findById(educationalPathDTO.getDegreeCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Degree Course not found"));

        EducationalPath newEducationalPath = new EducationalPath(professional, university, faculty, degreeCourse);

        professional.addEducationalPath(newEducationalPath);

        professionalRepository.save(professional);

        return newEducationalPath;
    }

    @Transactional
    public void removeEducationalPath(String professionalUsername, Long educationalPathId) {
        EducationalPath educationalPathToRemove = educationalPathRepository.findByProfessionalAppUserUsernameAndId(professionalUsername, educationalPathId)
                .orElseThrow(() -> new EntityNotFoundException("Educational Path not found"));

        educationalPathRepository.delete(educationalPathToRemove);
    }

}



