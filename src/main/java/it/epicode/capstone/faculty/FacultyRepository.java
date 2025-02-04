package it.epicode.capstone.faculty;

import it.epicode.capstone.degree_courses.DegreeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    boolean existsByName(String name);

    @Query("SELECT f.degreeCourses FROM Faculty f WHERE f.id = :facultyId")
    List<DegreeCourse> findDegreeCoursesByFacultyId(@Param("facultyId") Long facultyId);
}
