package it.epicode.capstone.active_users.student;

import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.favourites.Favourite;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Set<String> interests;

    @OneToOne
    @MapsId
    private AppUser appUser;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favourite> favorites = new HashSet<>();
}

