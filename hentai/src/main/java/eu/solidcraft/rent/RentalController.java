package eu.solidcraft.rent;

import eu.solidcraft.shared.FilmDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/rent")
@RestController
@AllArgsConstructor
class RentalController {

    private final RentalFacade facade;

    @PostMapping
    void rent(@RequestBody FilmRentalRequest filmRentalRequest, Principal principal) {
        String userId = principal.getName();

        filmRentalRequest.getFilms().forEach(
                film -> facade.rentFilm(userId, FilmDto.builder()
                        .build()));
    }

}
