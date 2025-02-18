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
        Optional<AppUser> adminUser = appUserService.findByUsername("Admin");
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
        Optional<AppUser> student = appUserService.findByUsername("Student");
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

        Optional<AppUser> professional = appUserService.findByUsername("Professional");
        if (professional.isEmpty()) {
            RegisterDTO professionalRequest = new RegisterDTO();
            professionalRequest.setUsername("Professional");
            professionalRequest.setPassword("professionalpwd");
            professionalRequest.setEmail("professional@epicode.it");
            professionalRequest.setName("Professional");
            professionalRequest.setSurname("Professional");
            professionalRequest.setDateOfBirth(LocalDate.of(1978,3,30));
            appUserService.registerUser(professionalRequest, null, Set.of(Role.ROLE_PROFESSIONAL));

            // Professional 1: John Smith
            RegisterDTO professional1 = new RegisterDTO();
            professional1.setUsername("john.smith");
            professional1.setPassword("John@1980");
            professional1.setEmail("john.smith@example.com");
            professional1.setName("John");
            professional1.setSurname("Smith");
            professional1.setDateOfBirth(LocalDate.of(1980, 5, 12));
            appUserService.registerUser(professional1, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 2: Mary Johnson
            RegisterDTO professional2 = new RegisterDTO();
            professional2.setUsername("mary.johnson");
            professional2.setPassword("Mary@1975");
            professional2.setEmail("mary.johnson@example.com");
            professional2.setName("Mary");
            professional2.setSurname("Johnson");
            professional2.setDateOfBirth(LocalDate.of(1975, 8, 22));
            appUserService.registerUser(professional2, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 3: Robert Williams
            RegisterDTO professional3 = new RegisterDTO();
            professional3.setUsername("robert.williams");
            professional3.setPassword("Robert@1982");
            professional3.setEmail("robert.williams@example.com");
            professional3.setName("Robert");
            professional3.setSurname("Williams");
            professional3.setDateOfBirth(LocalDate.of(1982, 11, 3));
            appUserService.registerUser(professional3, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 4: Patricia Brown
            RegisterDTO professional4 = new RegisterDTO();
            professional4.setUsername("patricia.brown");
            professional4.setPassword("Patricia@1978");
            professional4.setEmail("patricia.brown@example.com");
            professional4.setName("Patricia");
            professional4.setSurname("Brown");
            professional4.setDateOfBirth(LocalDate.of(1978, 3, 30));
            appUserService.registerUser(professional4, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 5: Michael Jones
            RegisterDTO professional5 = new RegisterDTO();
            professional5.setUsername("michael.jones");
            professional5.setPassword("Michael@1985");
            professional5.setEmail("michael.jones@example.com");
            professional5.setName("Michael");
            professional5.setSurname("Jones");
            professional5.setDateOfBirth(LocalDate.of(1985, 1, 15));
            appUserService.registerUser(professional5, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 6: Linda Garcia
            RegisterDTO professional6 = new RegisterDTO();
            professional6.setUsername("linda.garcia");
            professional6.setPassword("Linda@1972");
            professional6.setEmail("linda.garcia@example.com");
            professional6.setName("Linda");
            professional6.setSurname("Garcia");
            professional6.setDateOfBirth(LocalDate.of(1972, 4, 18));
            appUserService.registerUser(professional6, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 7: David Miller
            RegisterDTO professional7 = new RegisterDTO();
            professional7.setUsername("david.miller");
            professional7.setPassword("David@1983");
            professional7.setEmail("david.miller@example.com");
            professional7.setName("David");
            professional7.setSurname("Miller");
            professional7.setDateOfBirth(LocalDate.of(1983, 9, 7));
            appUserService.registerUser(professional7, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 8: Barbara Davis
            RegisterDTO professional8 = new RegisterDTO();
            professional8.setUsername("barbara.davis");
            professional8.setPassword("Barbara@1979");
            professional8.setEmail("barbara.davis@example.com");
            professional8.setName("Barbara");
            professional8.setSurname("Davis");
            professional8.setDateOfBirth(LocalDate.of(1979, 6, 25));
            appUserService.registerUser(professional8, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 9: James Rodriguez
            RegisterDTO professional9 = new RegisterDTO();
            professional9.setUsername("james.rodriguez");
            professional9.setPassword("James@1981");
            professional9.setEmail("james.rodriguez@example.com");
            professional9.setName("James");
            professional9.setSurname("Rodriguez");
            professional9.setDateOfBirth(LocalDate.of(1981, 12, 5));
            appUserService.registerUser(professional9, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 10: Elizabeth Martinez
            RegisterDTO professional10 = new RegisterDTO();
            professional10.setUsername("elizabeth.martinez");
            professional10.setPassword("Elizabeth@1984");
            professional10.setEmail("elizabeth.martinez@example.com");
            professional10.setName("Elizabeth");
            professional10.setSurname("Martinez");
            professional10.setDateOfBirth(LocalDate.of(1984, 7, 14));
            appUserService.registerUser(professional10, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 11: William Hernandez
            RegisterDTO professional11 = new RegisterDTO();
            professional11.setUsername("william.hernandez");
            professional11.setPassword("William@1976");
            professional11.setEmail("william.hernandez@example.com");
            professional11.setName("William");
            professional11.setSurname("Hernandez");
            professional11.setDateOfBirth(LocalDate.of(1976, 10, 20));
            appUserService.registerUser(professional11, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 12: Jennifer Lopez
            RegisterDTO professional12 = new RegisterDTO();
            professional12.setUsername("jennifer.lopez");
            professional12.setPassword("Jennifer@1987");
            professional12.setEmail("jennifer.lopez@example.com");
            professional12.setName("Jennifer");
            professional12.setSurname("Lopez");
            professional12.setDateOfBirth(LocalDate.of(1987, 2, 11));
            appUserService.registerUser(professional12, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 13: Richard Gonzalez
            RegisterDTO professional13 = new RegisterDTO();
            professional13.setUsername("richard.gonzalez");
            professional13.setPassword("Richard@1980");
            professional13.setEmail("richard.gonzalez@example.com");
            professional13.setName("Richard");
            professional13.setSurname("Gonzalez");
            professional13.setDateOfBirth(LocalDate.of(1980, 3, 3));
            appUserService.registerUser(professional13, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 14: Maria Wilson
            RegisterDTO professional14 = new RegisterDTO();
            professional14.setUsername("maria.wilson");
            professional14.setPassword("Maria@1973");
            professional14.setEmail("maria.wilson@example.com");
            professional14.setName("Maria");
            professional14.setSurname("Wilson");
            professional14.setDateOfBirth(LocalDate.of(1973, 8, 30));
            appUserService.registerUser(professional14, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 15: Joseph Anderson
            RegisterDTO professional15 = new RegisterDTO();
            professional15.setUsername("joseph.anderson");
            professional15.setPassword("Joseph@1982");
            professional15.setEmail("joseph.anderson@example.com");
            professional15.setName("Joseph");
            professional15.setSurname("Anderson");
            professional15.setDateOfBirth(LocalDate.of(1982, 5, 27));
            appUserService.registerUser(professional15, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 16: Susan Thomas
            RegisterDTO professional16 = new RegisterDTO();
            professional16.setUsername("susan.thomas");
            professional16.setPassword("Susan@1978");
            professional16.setEmail("susan.thomas@example.com");
            professional16.setName("Susan");
            professional16.setSurname("Thomas");
            professional16.setDateOfBirth(LocalDate.of(1978, 9, 17));
            appUserService.registerUser(professional16, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 17: Charles Taylor
            RegisterDTO professional17 = new RegisterDTO();
            professional17.setUsername("charles.taylor");
            professional17.setPassword("Charles@1986");
            professional17.setEmail("charles.taylor@example.com");
            professional17.setName("Charles");
            professional17.setSurname("Taylor");
            professional17.setDateOfBirth(LocalDate.of(1986, 11, 28));
            appUserService.registerUser(professional17, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 18: Karen Moore
            RegisterDTO professional18 = new RegisterDTO();
            professional18.setUsername("karen.moore");
            professional18.setPassword("Karen@1974");
            professional18.setEmail("karen.moore@example.com");
            professional18.setName("Karen");
            professional18.setSurname("Moore");
            professional18.setDateOfBirth(LocalDate.of(1974, 12, 12));
            appUserService.registerUser(professional18, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 19: Christopher Jackson
            RegisterDTO professional19 = new RegisterDTO();
            professional19.setUsername("christopher.jackson");
            professional19.setPassword("Christopher@1983");
            professional19.setEmail("christopher.jackson@example.com");
            professional19.setName("Christopher");
            professional19.setSurname("Jackson");
            professional19.setDateOfBirth(LocalDate.of(1983, 4, 9));
            appUserService.registerUser(professional19, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 20: Nancy Martin
            RegisterDTO professional20 = new RegisterDTO();
            professional20.setUsername("nancy.martin");
            professional20.setPassword("Nancy@1977");
            professional20.setEmail("nancy.martin@example.com");
            professional20.setName("Nancy");
            professional20.setSurname("Martin");
            professional20.setDateOfBirth(LocalDate.of(1977, 6, 19));
            appUserService.registerUser(professional20, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 21: Daniel Lee
            RegisterDTO professional21 = new RegisterDTO();
            professional21.setUsername("daniel.lee");
            professional21.setPassword("Daniel@1985");
            professional21.setEmail("daniel.lee@example.com");
            professional21.setName("Daniel");
            professional21.setSurname("Lee");
            professional21.setDateOfBirth(LocalDate.of(1985, 7, 8));
            appUserService.registerUser(professional21, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 22: Lisa Perez
            RegisterDTO professional22 = new RegisterDTO();
            professional22.setUsername("lisa.perez");
            professional22.setPassword("Lisa@1979");
            professional22.setEmail("lisa.perez@example.com");
            professional22.setName("Lisa");
            professional22.setSurname("Perez");
            professional22.setDateOfBirth(LocalDate.of(1979, 1, 23));
            appUserService.registerUser(professional22, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 23: Matthew Thompson
            RegisterDTO professional23 = new RegisterDTO();
            professional23.setUsername("matthew.thompson");
            professional23.setPassword("Matthew@1980");
            professional23.setEmail("matthew.thompson@example.com");
            professional23.setName("Matthew");
            professional23.setSurname("Thompson");
            professional23.setDateOfBirth(LocalDate.of(1980, 10, 10));
            appUserService.registerUser(professional23, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 24: Betty White
            RegisterDTO professional24 = new RegisterDTO();
            professional24.setUsername("betty.white");
            professional24.setPassword("Betty@1976");
            professional24.setEmail("betty.white@example.com");
            professional24.setName("Betty");
            professional24.setSurname("White");
            professional24.setDateOfBirth(LocalDate.of(1976, 2, 4));
            appUserService.registerUser(professional24, null, Set.of(Role.ROLE_PROFESSIONAL));

// Professional 25: Anthony Harris
            RegisterDTO professional25 = new RegisterDTO();
            professional25.setUsername("anthony.harris");
            professional25.setPassword("Anthony@1984");
            professional25.setEmail("anthony.harris@example.com");
            professional25.setName("Anthony");
            professional25.setSurname("Harris");
            professional25.setDateOfBirth(LocalDate.of(1984, 3, 29));
            appUserService.registerUser(professional25, null, Set.of(Role.ROLE_PROFESSIONAL));

        }
    }
}

