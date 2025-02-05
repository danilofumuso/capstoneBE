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

    private Set<Long> sectorsOfInterestId;

    private List<EducationalPathDTO> educationalPaths;
    private Long professionId;
}