package it.epicode.capstone.active_users.professional;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.educational_path.EducationalPath;
import it.epicode.capstone.profession.Profession;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<EducationalPath> educationalPaths = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @Column(name = "written_story", length = 5000)
    private String writtenStory;

    @Column(name = "video_story")
    private String videoStory;

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

    public void addEducationalPath(EducationalPath path) {
        this.educationalPaths.add(path);
        path.setProfessional(this);
    }
}
