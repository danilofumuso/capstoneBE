package it.epicode.capstone.favourites;

import it.epicode.capstone.active_users.professional.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/{studentId}")
    public ResponseEntity<Page<Professional>> getFavourites(@PathVariable Long studentId, Pageable pageable) {
        return ResponseEntity.ok(favouriteService.getFavourites(studentId, pageable));
    }

    @PostMapping
    public ResponseEntity<Favourite> addFavourite(@RequestParam Long studentId, @RequestParam Long professionalId) {

        return new ResponseEntity<>(favouriteService.addFavourite(studentId, professionalId), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> removeFavourite(@RequestParam Long studentId, @RequestParam Long professionalId) {
        favouriteService.removeFavourite(studentId, professionalId);
        return ResponseEntity.ok("Professional removed from favourites");
    }

}
