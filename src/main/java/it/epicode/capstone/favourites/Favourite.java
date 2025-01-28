package it.epicode.capstone.favourites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.student.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "favourites")
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false)
    private Professional professional;

    private LocalDateTime savedAt = LocalDateTime.now();
}
