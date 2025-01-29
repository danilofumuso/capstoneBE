package it.epicode.capstone.active_users.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByAppUserUsername(String username);
}
