package it.epicode.capstone.faculty;

import it.epicode.capstone.degree_courses.DegreeCourseDTO;
import lombok.Data;

import java.util.Set;

@Data
public class FacultyDTO {
    private Long id;
    private String name;
    private Set<DegreeCourseDTO> degreeCourses;
}
