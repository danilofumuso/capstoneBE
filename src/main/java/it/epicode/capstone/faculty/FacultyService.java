package it.epicode.capstone.faculty;

import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.degree_courses.DegreeCourseDTO;
import it.epicode.capstone.degree_courses.DegreeCourseRepository;
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
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;


    public Page<Faculty> getAllFaculties(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    @Transactional
    public Faculty createFaculty(FacultyDTO facultyDTO) {
        if (facultyRepository.existsByName(facultyDTO.getName())) {
            throw new EntityExistsException("Faculty already exists");
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());

        if (facultyDTO.getDegreeCoursesId() != null) {
            Set<DegreeCourse> degreeCourses = new HashSet<>();
            for (Long courseId : facultyDTO.getDegreeCoursesId()) {
                DegreeCourse degreeCourse = degreeCourseRepository.findById(courseId)
                        .orElseThrow(() -> new EntityNotFoundException("DegreeCourse not found"));
                degreeCourses.add(degreeCourse);
            }
            faculty.setDegreeCourses(degreeCourses);
        }
        return facultyRepository.save(faculty);
    }

    @Transactional
    public Faculty updateFaculty(Long facultyId, FacultyDTO facultyDTO) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty not found"));

        if (facultyDTO.getName() != null) {
            faculty.setName(facultyDTO.getName());
        }

        if (facultyDTO.getDegreeCoursesId() != null) {
            Set<DegreeCourse> existingCourses = faculty.getDegreeCourses();
            Set<DegreeCourse> updatedCourses = new HashSet<>(existingCourses);

            for (Long courseId : facultyDTO.getDegreeCoursesId()) {
                DegreeCourse degreeCourse = degreeCourseRepository.findById(courseId)
                        .orElseThrow(() -> new EntityNotFoundException("DegreeCourse not found"));
                updatedCourses.add(degreeCourse);
            }
            faculty.setDegreeCourses(updatedCourses);
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }
}
