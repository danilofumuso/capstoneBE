package it.epicode.capstone.university;

import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.faculty.FacultyRepository;
import it.epicode.capstone.faculty.FacultyforUniversityDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public Page<University> getAllUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    private Set<Faculty> processFaculties(Set<FacultyforUniversityDTO> facultiesDTO) {
        Set<Faculty> faculties = new HashSet<>();

        if (facultiesDTO != null) {
            facultiesDTO.forEach(facultyDTO -> {
                Faculty faculty;
                if (facultyDTO.getId() != null) {
                    faculty = facultyRepository.findById(facultyDTO.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));
                System.out.println(faculty.getName());
                } else {
                    faculty = new Faculty();
                    faculty.setName(facultyDTO.getName());
                }
                faculties.add(faculty);
                System.out.println(faculties);
            });
        }
        System.out.println(faculties);
        return faculties;
    }

    @Transactional
    public University createUniversity(UniversityDTO universityDTO) {
        if (universityRepository.existsByName(universityDTO.getName())) {
            throw new EntityExistsException("University already exists");
        }

        University university = new University();
        university.setName(universityDTO.getName());

        if (universityDTO.getFaculties() != null) {
            Set<Faculty> faculties = processFaculties(universityDTO.getFaculties());
            university.getFaculties().addAll(faculties);
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

        if (universityDTO.getFaculties() != null) {
            Set<Faculty> facultiesToAdd = processFaculties(universityDTO.getFaculties());
            university.getFaculties().addAll(facultiesToAdd);
        }

        return universityRepository.save(university);
    }

    public void deleteUniversity(Long universityId) {
        universityRepository.deleteById(universityId);
    }

}
