package it.epicode.capstone.active_users.professional;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.epicode.capstone.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "professionals")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Set<String> universities;

    private Set<String> faculties;

    private Set<String> courses;

    @Column(name = "academic_titles")
    private Set<String> academicTitles;

    private String occupation;

    @Column(name= "occupation_area")
    private String occupationArea;

    @Column(name= "academic_career_path")
    private String academicCareerPath;

    @Column(name= "academic_career_path_video")
    private String academicCareerPathVideo;

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    private AppUser appUser;
}
