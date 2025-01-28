package it.epicode.capstone.favourites;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.professional.ProfessionalRepository;
import it.epicode.capstone.active_users.student.Student;
import it.epicode.capstone.active_users.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavouriteService {
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessionalRepository professionalRepository;

    public void addFavourite(Long studentId, Long professionalId) {
        if (favouriteRepository.existsByStudentIdAndProfessionalId(studentId, professionalId)) {
            throw new RuntimeException("This professional is already in your favourites.");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Favourite favourite = new Favourite();
        favourite.setStudent(student);
        favourite.setProfessional(professional);

        favouriteRepository.save(favourite);
    }

    public void removeFavourite(Long studentId, Long professionalId) {
        Optional<Favourite> favouriteToRemove = favouriteRepository.findByStudentIdAndProfessionalId(studentId, professionalId);

        favouriteToRemove.ifPresent(favouriteRepository::delete);
    }

    public List<Professional> getFavourites(Long studentId) {
        return favouriteRepository.findByStudentId(studentId).stream()
                .map(Favourite::getProfessional)
                .collect(Collectors.toList());
    }
}
