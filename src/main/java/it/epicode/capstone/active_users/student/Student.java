package it.epicode.capstone.active_users.student;

import com.fasterxml.jackson.annotation.*;
import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.favourite.Favourite;
import it.epicode.capstone.sector.Sector;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    private AppUser appUser;

    @ManyToMany
    private Set<Sector> sectorsOfInterest = new LinkedHashSet<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Favourite> favorites = new LinkedHashSet<>();
}
