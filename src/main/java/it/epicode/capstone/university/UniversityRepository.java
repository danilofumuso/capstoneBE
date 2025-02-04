package it.epicode.capstone.university;

import it.epicode.capstone.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {
    Optional<University> findByName(String universityName);

    boolean existsByName(String universityName);

    @Query("SELECT u.faculties FROM University u WHERE u.id = :universityId")
    List<Faculty> findFacultiesByUniversityId(@Param("universityId") Long universityId);
}
