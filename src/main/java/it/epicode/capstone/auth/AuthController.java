package it.epicode.capstone.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(path = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<AppUser> registerAdmin(@RequestParam("appUser") String appUser,
                                                   @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture){

        RegisterDTO registerDTO;

        try {
            registerDTO = objectMapper.readValue(appUser, RegisterDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(appUserService.registerUser(registerDTO, profilePicture, Set.of(Role.ROLE_ADMIN)), HttpStatus.CREATED);

    }

    @PostMapping(path = "/register/student", consumes = {"multipart/form-data"})
    public ResponseEntity<AppUser> registerStudent(@RequestParam("appUser") String appUser,
                                                   @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture){

        RegisterDTO registerDTO;

        try {
            registerDTO = objectMapper.readValue(appUser, RegisterDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(appUserService.registerUser(registerDTO, profilePicture, Set.of(Role.ROLE_STUDENT)), HttpStatus.CREATED);

    }

     @PostMapping(path = "/register/professional", consumes = {"multipart/form-data"})
    public ResponseEntity<AppUser> registerProfessionista(@RequestParam("appUser") String appUser,
                                            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture){

        RegisterDTO registerDTO;

        try {
            registerDTO = objectMapper.readValue(appUser, RegisterDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(appUserService.registerUser(registerDTO, profilePicture, Set.of(Role.ROLE_PROFESSIONAL)), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = appUserService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(authResponse);
    }
}
