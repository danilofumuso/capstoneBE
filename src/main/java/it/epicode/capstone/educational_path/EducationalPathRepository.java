package it.epicode.capstone.educational_path;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationalPathRepository extends JpaRepository<EducationalPath, Long> {
    List<EducationalPath> findByProfessionalAppUserUsername(String username);

    boolean existsByProfessionalAppUserUsernameAndDegreeCourseId(String professionalUsername, Long degreeCourseId);

    Optional<EducationalPath> findByProfessionalAppUserUsernameAndId(String professionalUsername, Long educationalPathId);
}

