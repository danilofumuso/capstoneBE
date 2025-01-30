package it.epicode.capstone.favourite;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.professional.ProfessionalRepository;
import it.epicode.capstone.active_users.student.Student;
import it.epicode.capstone.active_users.student.StudentRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavouriteService {

    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    public Page<Favourite> getFavourites(String studentUsername, Pageable pageable) {
        return favouriteRepository.findByStudentAppUserUsername(studentUsername, pageable);
    }

    public Favourite addFavourite(String studentUsername, Long professionalId) {
        if (favouriteRepository.existsByStudentAppUserUsernameAndProfessionalId(studentUsername, professionalId)) {
            throw new EntityExistsException("This professional is already in your favourites");
        }

        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

        Favourite favourite = new Favourite();
        favourite.setStudent(student);
        favourite.setProfessional(professional);

        return favouriteRepository.save(favourite);
    }

    public void removeFavourite(String studentUsername, Long favouriteId) {
        Favourite favouriteToRemove = favouriteRepository.findByStudentAppUserUsernameAndId(studentUsername, favouriteId)
                .orElseThrow(() -> new EntityNotFoundException("Favourite not found"));

        favouriteRepository.delete(favouriteToRemove);
    }
}
