package it.epicode.capstone.university;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String faculty;

    private String degreeCourse;

}
