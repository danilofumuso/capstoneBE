package it.epicode.capstone.active_users.student;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentDTO {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;
    private Set<Long> sectorsOfInterestId;
}
