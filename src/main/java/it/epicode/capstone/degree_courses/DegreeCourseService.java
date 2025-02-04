package it.epicode.capstone.degree_courses;


import it.epicode.capstone.faculty.FacultyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DegreeCourseService {

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public Page<DegreeCourse> getAllDegreeCourses(Pageable pageable) {
        return degreeCourseRepository.findAll(pageable);
    }

    public List<DegreeCourse> getDegreeCoursesByFaculty(Long facultyId) {
        return facultyRepository.findDegreeCoursesByFacultyId(facultyId);
    }

    @Transactional
    public DegreeCourse createDegreeCourse(DegreeCourseDTO degreeCourseDTO) {
        DegreeCourse degreeCourse = new DegreeCourse();
        degreeCourse.setName(degreeCourseDTO.getName());
        return degreeCourseRepository.save(degreeCourse);
    }

    @Transactional
    public DegreeCourse updateDegreeCourse(Long degreeCourseId, DegreeCourseDTO degreeCourseDTO) {
        DegreeCourse degreeCourse = degreeCourseRepository.findById(degreeCourseId)
                .orElseThrow(() -> new EntityNotFoundException("DegreeCourse not found"));
        degreeCourse.setName(degreeCourseDTO.getName());
        return degreeCourseRepository.save(degreeCourse);
    }

    public void deleteDegreeCourse(Long degreeCourseId) {
        degreeCourseRepository.deleteById(degreeCourseId);
    }
}
