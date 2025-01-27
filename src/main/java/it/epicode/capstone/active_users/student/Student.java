package it.epicode.capstone.active_users.student;

import it.epicode.capstone.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private AppUser appUser;

}
