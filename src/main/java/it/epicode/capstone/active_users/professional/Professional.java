package it.epicode.capstone.active_users.professional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.profession.Profession;
import it.epicode.capstone.university.University;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "professionals")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    private AppUser appUser;

    @ManyToMany
    private Set<University> universities = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @Column(name = "written_story")
    private String writtenStory;

    @Column(name = "video_story")
    private String videoStory;

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

}
