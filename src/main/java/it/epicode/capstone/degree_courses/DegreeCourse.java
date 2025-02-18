package it.epicode.capstone.degree_courses;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "degree_courses")
public class DegreeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "You must enter a name!")
    @Column(unique = true)
    private String name;

}
