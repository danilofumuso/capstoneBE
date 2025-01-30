package it.epicode.capstone.favourite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Page<Favourite>> getFavourites(@AuthenticationPrincipal UserDetails student, Pageable pageable) {
        return ResponseEntity.ok(favouriteService.getFavourites(student.getUsername(), pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Favourite> addFavourite(@AuthenticationPrincipal UserDetails student, @RequestParam Long professionalId) {

        return new ResponseEntity<>(favouriteService.addFavourite(student.getUsername(), professionalId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{favouriteId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<String> removeFavourite(@AuthenticationPrincipal UserDetails student, @PathVariable Long favouriteId) {
        favouriteService.removeFavourite(student.getUsername(), favouriteId);
        return new ResponseEntity<>("Professional removed from favourites", HttpStatus.NO_CONTENT);
    }
}
