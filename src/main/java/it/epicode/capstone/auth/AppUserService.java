package it.epicode.capstone.auth;

import it.epicode.capstone.active_users.professional.Professional;
import it.epicode.capstone.active_users.professional.ProfessionalRepository;
import it.epicode.capstone.active_users.student.Student;
import it.epicode.capstone.active_users.student.StudentRepository;
import it.epicode.capstone.cloudinary.CloudinaryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Transactional
    public AppUser registerUser(RegisterRequest registerRequest, MultipartFile profilePicture, Set<Role> roles) {
        if (appUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new EntityExistsException("Username already used!");
        }

        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(registerRequest, appUser);
        if (profilePicture != null && !profilePicture.isEmpty()) {
            appUser.setProfilePicture(cloudinaryService.uploader(profilePicture, "usersProfilePictures").get("url").toString());
        }

        appUser.setRoles(roles);

        if (roles.contains(Role.ROLE_STUDENT)) {
            Student student = new Student();
            student.setAppUser(appUser);
            studentRepository.save(student);
        }else if (roles.contains(Role.ROLE_PROFESSIONAL)){
            Professional professional=new Professional();
            professional.setAppUser(appUser);
            professionalRepository.save(professional);
        }

        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public AuthResponse authenticateUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            AppUser appUser = loadUserByUsername(username);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(jwtTokenUtil.generateToken(userDetails));

            authResponse.setUser(appUser);

            return authResponse;

        } catch (AuthenticationException e) {
            throw new SecurityException("Invalid credentials", e);
        }
    }

    public AppUser loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));

        return appUser;
    }
}
