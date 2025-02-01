package it.epicode.capstone.degree_courses;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeCourseRepository extends JpaRepository<DegreeCourse, Long> {
    Page<DegreeCourse> findByFaculties_Name(String facultyName, Pageable pageable);
}

