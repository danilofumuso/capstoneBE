package it.epicode.capstone.auth;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String username;
    private String email;
    private String password;
}
