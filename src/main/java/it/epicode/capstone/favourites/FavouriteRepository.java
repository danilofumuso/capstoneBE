package it.epicode.capstone.favourites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    boolean existsByStudentIdAndProfessionalId(Long studentId, Long professionalId);

    Optional<Favourite> findByStudentIdAndProfessionalId(Long studentId, Long professionalId);

    Page<Favourite> findByStudentId(Long studentId, Pageable pageable);
}
