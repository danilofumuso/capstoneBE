package it.epicode.capstone.auth;

import it.epicode.capstone.educational_path.EducationalPathDTO;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class RegisterDTO {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;

    private Set<String> sectorsOfInterest;

    private List<EducationalPathDTO> educationalPaths;
    private String professionName;
}