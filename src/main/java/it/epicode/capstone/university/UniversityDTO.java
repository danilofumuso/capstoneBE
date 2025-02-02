package it.epicode.capstone.university;

import it.epicode.capstone.faculty.FacultyDTO;
import lombok.Data;

import java.util.Set;

@Data
public class UniversityDTO {
    private String name;
    private Set<Long> facultiesId;
}
