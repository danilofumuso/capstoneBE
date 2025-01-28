package it.epicode.capstone.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            RegisterDTO adminRequest = new RegisterDTO();
            adminRequest.setUsername("Admin");
            adminRequest.setPassword("adminpwd");
            adminRequest.setEmail("admin@epicode.it");
            adminRequest.setName("Admin");
            adminRequest.setSurname("Admin");
            adminRequest.setDateOfBirth(LocalDate.of(1998,11,12));
            appUserService.registerUser(adminRequest, null, Set.of(Role.ROLE_ADMIN));
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> student = appUserService.findByUsername("student");
        if (student.isEmpty()) {
            RegisterDTO studentRequest = new RegisterDTO();
            studentRequest.setUsername("Student");
            studentRequest.setPassword("studentpwd");
            studentRequest.setEmail("student@epicode.it");
            studentRequest.setName("Student");
            studentRequest.setSurname("Student");
            studentRequest.setDateOfBirth(LocalDate.of(1991, 5,21));
            appUserService.registerUser(studentRequest, null, Set.of(Role.ROLE_STUDENT));
        }

        Optional<AppUser> professional = appUserService.findByUsername("professional");
        if (professional.isEmpty()) {
            RegisterDTO professionalRequest = new RegisterDTO();
            professionalRequest.setUsername("Professional");
            professionalRequest.setPassword("professionalpwd");
            professionalRequest.setEmail("professional@epicode.it");
            professionalRequest.setName("Professional");
            professionalRequest.setSurname("Professional");
            professionalRequest.setDateOfBirth(LocalDate.of(1978,3,30));
            appUserService.registerUser(professionalRequest, null, Set.of(Role.ROLE_PROFESSIONAL));
        }
    }
}

