package it.epicode.capstone.auth;

import com.fasterxml.jackson.annotation.*;
import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.student.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "You must enter a name!")
    private String name;

    @NotBlank(message = "You must enter a surname!")
    private String surname;

    @PastOrPresent(message = "Date of birth can't be in the future!")
    private LocalDate dateOfBirth;

    @NotBlank(message = "You must enter a username!")
    @Column(nullable = false, unique = true)
    private String username;

    @Email(message = "You must enter an email!")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "Enter a password of at least 6 characters!")
    @Column(nullable = false)
    private String password;

    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    private Professional professional;

}
