package it.epicode.capstone.faculty;

import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.degree_courses.DegreeCourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;


    public Page<Faculty> getAllFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    public Page<Faculty> getAllFacultiesByUniversity(String universityName, Pageable pageable) {
        return facultyRepository.findByUniversity_Name(universityName, pageable);
    }

    @Transactional
    public Faculty createFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());

        if (facultyDTO.getDegreeCourses() != null) {
            facultyDTO.getDegreeCourses().forEach(degreeCourseDTO -> {
                DegreeCourse degreeCourse;
                if (degreeCourseDTO.getId() != null) {
                    degreeCourse = degreeCourseRepository.findById(degreeCourseDTO.getId())
                            .orElseThrow(() -> new EntityNotFoundException("DegreeCourse not found"));
                } else {
                    degreeCourse = new DegreeCourse();
                    degreeCourse.setName(degreeCourseDTO.getName());
                }
                faculty.getDegreeCourses().add(degreeCourse);
            });
        }

        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long facultyId, FacultyDTO facultyDTO) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        faculty.setName(facultyDTO.getName());

        if (facultyDTO.getDegreeCourses() != null) {
            facultyDTO.getDegreeCourses().forEach(degreeCourseDTO -> {
                DegreeCourse degreeCourse;
                if (degreeCourseDTO.getId() != null) {
                    degreeCourse = degreeCourseRepository.findById(degreeCourseDTO.getId())
                            .orElseThrow(() -> new EntityNotFoundException("DegreeCourse not found"));
                } else {
                    degreeCourse = new DegreeCourse();
                    degreeCourse.setName(degreeCourseDTO.getName());
                }
                faculty.getDegreeCourses().add(degreeCourse);
            });
        }

        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }
}
