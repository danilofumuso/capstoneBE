package it.epicode.capstone.favourites;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.professional.ProfessionalRepository;
import it.epicode.capstone.active_users.student.Student;
import it.epicode.capstone.active_users.student.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    public Favourite addFavourite(Long studentId, Long professionalId) {
        if (favouriteRepository.existsByStudentIdAndProfessionalId(studentId, professionalId)) {
            throw new RuntimeException("This professional is already in your favourites");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        Favourite favourite = new Favourite();
        favourite.setStudent(student);
        favourite.setProfessional(professional);

        return favouriteRepository.save(favourite);
    }

    public void removeFavourite(Long studentId, Long professionalId) {
        Optional<Favourite> favouriteToRemove = favouriteRepository.findByStudentIdAndProfessionalId(studentId, professionalId);

        favouriteToRemove.ifPresent(favouriteRepository::delete);
    }

    public Page<Favourite> getFavourites(Long studentId, Pageable pageable) {
        return favouriteRepository.findByStudentId(studentId, pageable);
    }
}
