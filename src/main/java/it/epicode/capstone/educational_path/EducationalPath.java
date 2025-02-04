package it.epicode.capstone.educational_path;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.university.University;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "educational_paths",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"professional_id", "university_id", "faculty_id", "degree_course_id"})
        })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EducationalPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id")
    @EqualsAndHashCode.Include
    private Professional professional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    @EqualsAndHashCode.Include
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    @EqualsAndHashCode.Include
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "degree_course_id")
    @EqualsAndHashCode.Include
    private DegreeCourse degreeCourse;

    public EducationalPath(Professional professional,
                           University university,
                           Faculty faculty,
                           DegreeCourse degreeCourse) {
        this.professional = professional;
        this.university = university;
        this.faculty = faculty;
        this.degreeCourse = degreeCourse;
    }

}
