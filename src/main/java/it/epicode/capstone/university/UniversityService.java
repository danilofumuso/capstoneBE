package it.epicode.capstone.university;

import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.faculty.FacultyRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Transactional
    public University createUniversity(UniversityDTO universityDTO) {
        if (universityRepository.existsByName(universityDTO.getName())) {
            throw new EntityExistsException("University already exists");
        }
        University university = new University();
        university.setName(universityDTO.getName());

        if (universityDTO.getFacultiesId() != null) {
            Set<Faculty> faculties = new HashSet<>();
            for (Long facultyId : universityDTO.getFacultiesId()) {
                Faculty faculty = facultyRepository.findById(facultyId)
                        .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));
                faculties.add(faculty);
            }
            university.setFaculties(faculties);
        }
        return universityRepository.save(university);
    }

    @Transactional
    public University updateUniversity(Long universityId, UniversityDTO universityDTO) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found"));

        if (universityDTO.getName() != null) {
            university.setName(universityDTO.getName());
        }

        if (universityDTO.getFacultiesId() != null) {
            Set<Faculty> existingFaculties = university.getFaculties();
            Set<Faculty> updatedFaculties = new HashSet<>(existingFaculties);

            for (Long facultyId : universityDTO.getFacultiesId()) {
                Faculty faculty = facultyRepository.findById(facultyId)
                        .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));
                updatedFaculties.add(faculty);
            }
            university.setFaculties(updatedFaculties);
        }
        return universityRepository.save(university);
    }

    public void deleteUniversity(Long universityId) {
        universityRepository.deleteById(universityId);
    }

}
