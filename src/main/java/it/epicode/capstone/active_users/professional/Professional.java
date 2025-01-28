package it.epicode.capstone.active_users.professional;

import it.epicode.capstone.auth.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

@Data
@Entity
@Table(name = "professionals")
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
    private MultipartFile academicCareerPathVideo;

    @Column(name = "curriculum_vitae")
    private MultipartFile curriculumVitae;

    @OneToOne
    @MapsId
    private AppUser appUser;
}
