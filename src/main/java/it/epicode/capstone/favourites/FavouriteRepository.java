package it.epicode.capstone.favourites;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite,Long> {

    boolean existsByStudentIdAndProfessionalId(Long studentId, Long professionalId);

    Optional<Favourite> findByStudentIdAndProfessionalId(Long studentId, Long professionalId);

    List<Favourite> findByStudentId(Long studentId);
}
