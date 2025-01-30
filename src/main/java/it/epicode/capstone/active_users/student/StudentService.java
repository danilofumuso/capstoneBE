package it.epicode.capstone.active_users.student;

import it.epicode.capstone.auth.AppUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
//
//    public Student getStudent(String studentUsername) {
//        return studentRepository.findByAppUserUsername(studentUsername)
//                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
//    }

    @Transactional
    public Student updateStudent(String studentUsername, StudentDTO studentDTO) {
        Student student = studentRepository.findByAppUserUsername(studentUsername)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        AppUser appUser = student.getAppUser();
        if (studentDTO.getName() != null) {
            appUser.setName(studentDTO.getName());
        }

        if (studentDTO.getSurname() != null) {
            appUser.setSurname(studentDTO.getSurname());
        }

        if (studentDTO.getDateOfBirth() != null) {
            appUser.setDateOfBirth(studentDTO.getDateOfBirth());
        }

        if (studentDTO.getUsername() != null) {
            appUser.setUsername(studentDTO.getUsername());
        }

        if (studentDTO.getEmail() != null) {
            appUser.setEmail(studentDTO.getEmail());
        }

        if (studentDTO.getPassword() != null) {
            appUser.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        }

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(String studentUsername) {
        studentRepository.deleteByAppUserUsername(studentUsername);
    }

}
