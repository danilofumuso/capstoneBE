package it.epicode.capstone.educational_path;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.degree_courses.DegreeCourse;
import it.epicode.capstone.faculty.Faculty;
import it.epicode.capstone.university.University;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "educational_paths")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EducationalPath {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @EqualsAndHashCode.Include
    private University university;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @EqualsAndHashCode.Include
    private Faculty faculty;

    @ManyToOne
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
