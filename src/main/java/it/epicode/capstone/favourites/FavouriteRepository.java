package it.epicode.capstone.favourites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
    Page<Favourite> findByStudentAppUserUsername(String username, Pageable pageable);

    boolean existsByStudentAppUserUsernameAndProfessionalId(String username, Long professionalId);

    Optional<Favourite> findByStudentAppUserUsernameAndId(String username, Long favouriteId);

}
