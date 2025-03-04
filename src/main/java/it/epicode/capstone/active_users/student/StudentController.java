package it.epicode.capstone.active_users.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Student> updateStudent(@AuthenticationPrincipal UserDetails student, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(student.getUsername(), studentDTO));
    }

    @PatchMapping("/sectorsOfInterest")
    public ResponseEntity<Student> updateSectorsOfInterest(@AuthenticationPrincipal UserDetails student,
                                                           @RequestBody StudentSectorsOfInterestDTO studentSectorsOfInterestDTO) {

        return ResponseEntity.ok(studentService.updateSectorsOfInterest(student.getUsername(), studentSectorsOfInterestDTO));
    }

    @PatchMapping(path = "/profilePicture", consumes = {"multipart/form-data"})
    public ResponseEntity<Student> updateProfilePicture(
            @AuthenticationPrincipal UserDetails student,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {

        return ResponseEntity.ok(studentService.updateProfilePicture(student.getUsername(), profilePicture));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_STUDENT') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteStudent(@AuthenticationPrincipal UserDetails student) {
        studentService.deleteStudent(student.getUsername());
        return new ResponseEntity<>("Student removed successfully", HttpStatus.NO_CONTENT);
    }

}
