package it.epicode.capstone.degree_courses;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "degree_courses")
public class DegreeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
