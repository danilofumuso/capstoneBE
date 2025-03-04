package it.epicode.capstone.active_users.student;

import it.epicode.capstone.auth.AppUser;
import it.epicode.capstone.cloudinary.CloudinaryService;
import it.epicode.capstone.sector.Sector;
import it.epicode.capstone.sector.SectorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Transactional
    public Student updateStudent(String studentUsername, StudentDTO studentDTO) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppUser appUser = student.getAppUser();
        if (studentDTO.getName() != null && !studentDTO.getName().isEmpty()) {
            appUser.setName(studentDTO.getName());
        }

        if (studentDTO.getSurname() != null && !studentDTO.getSurname().isEmpty()) {
            appUser.setSurname(studentDTO.getSurname());
        }

        if (studentDTO.getDateOfBirth() != null) {
            appUser.setDateOfBirth(studentDTO.getDateOfBirth());
        }

        if (studentDTO.getUsername() != null && !studentDTO.getUsername().isEmpty()) {
            appUser.setUsername(studentDTO.getUsername());
        }

        if (studentDTO.getEmail() != null && !studentDTO.getEmail().isEmpty()) {
            appUser.setEmail(studentDTO.getEmail());
        }

        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isEmpty()) {
            appUser.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        return studentRepository.save(student);
    }

    public Student updateSectorsOfInterest(String studentUsername, StudentSectorsOfInterestDTO studentSectorsOfInterestDTO) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (studentSectorsOfInterestDTO.getSectorsOfInterestId() != null) {
            Set<Sector> sectors = new HashSet<>();

            for (Long sectorId : studentSectorsOfInterestDTO.getSectorsOfInterestId()) {
                sectorRepository.findById(sectorId).ifPresent(sectors::add);
            }

            student.setSectorsOfInterest(sectors);
        }

        return studentRepository.save(student);
    }

    public Student updateProfilePicture(String studentUsername, MultipartFile profilePicture) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppUser appUser = student.getAppUser();

        if (profilePicture != null && !profilePicture.isEmpty()) {
            appUser.setProfilePicture(cloudinaryService.uploader(profilePicture, "profilePictures", "image").get("url").toString());
        } else {
            appUser.setProfilePicture(null);
        }

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(String studentUsername) {
        studentRepository.deleteByAppUserUsername(studentUsername);
    }

}
