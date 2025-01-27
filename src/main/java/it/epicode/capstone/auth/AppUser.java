package it.epicode.capstone.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.student.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Student student;

    @OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Professional professional;

}
