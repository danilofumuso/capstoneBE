package it.epicode.capstone.active_users.student;

import com.fasterxml.jackson.annotation.*;
import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.favourites.Favourite;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Set<String> interests = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    private AppUser appUser;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Favourite> favorites = new HashSet<>();
}

