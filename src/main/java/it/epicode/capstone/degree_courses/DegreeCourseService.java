package it.epicode.capstone.degree_courses;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DegreeCourseService {

    @Autowired
    private DegreeCourseRepository degreeCourseRepository;

    public Page<DegreeCourse> getAllDegreeCourses(Pageable pageable) {
        return degreeCourseRepository.findAll(pageable);
    }

    public Page<DegreeCourse> getAllDegreeCoursesByFaculty(String facultyName, Pageable pageable) {
        return degreeCourseRepository.findByFaculty_Name(facultyName, pageable);
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
