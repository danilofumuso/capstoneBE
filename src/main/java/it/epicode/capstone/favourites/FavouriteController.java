package it.epicode.capstone.favourites;

import it.epicode.capstone.active_users.professional.Professional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourites")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Professional>> getFavourites(@PathVariable Long studentId) {
        return ResponseEntity.ok(favouriteService.getFavourites(studentId));
    }

    @PostMapping
    public ResponseEntity<String> addFavourite(@RequestParam Long studentId, @RequestParam Long professionalId) {
        favouriteService.addFavourite(studentId, professionalId);
        return ResponseEntity.ok("Professional added to favourites");
    }

    @DeleteMapping
    public ResponseEntity<String> removeFavourite(@RequestParam Long studentId, @RequestParam Long professionalId) {
        favouriteService.removeFavourite(studentId, professionalId);
        return ResponseEntity.ok("Professional removed from favourites");
    }

}
