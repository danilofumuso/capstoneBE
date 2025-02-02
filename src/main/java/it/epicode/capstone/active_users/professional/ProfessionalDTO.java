package it.epicode.capstone.active_users.professional;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProfessionalDTO {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;
    private Set<String> universitiesNames;
    private Set<String> facultiesNames;
    private Set<String> degreeCoursesNames;
    private String professionName;
}
