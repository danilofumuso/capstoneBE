package it.epicode.capstone.favourites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Page<Favourite>> getFavourites(@PathVariable Long studentId, Pageable pageable) {
        return ResponseEntity.ok(favouriteService.getFavourites(studentId, pageable));
    }

    @PostMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Favourite> addFavourite(@PathVariable Long studentId, @RequestParam Long professionalId) {

        return new ResponseEntity<>(favouriteService.addFavourite(studentId, professionalId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<String> removeFavourite(@PathVariable Long studentId, @RequestParam Long professionalId) {
        favouriteService.removeFavourite(studentId, professionalId);
        return ResponseEntity.ok("Professional removed from favourites");
    }

}
