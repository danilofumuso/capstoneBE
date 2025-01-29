package it.epicode.capstone.active_users.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Student> updateStudent(@AuthenticationPrincipal UserDetails student, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(student.getUsername(), studentDTO));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<String> deleteStudent(@AuthenticationPrincipal UserDetails student) {
        studentService.deleteStudent(student.getUsername());
        return new ResponseEntity<>("Student removed successfully", HttpStatus.NO_CONTENT);
    }

}
