package eu.solidcraft.rent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import eu.solidcraft.shared.FilmDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/rent")
@RestController
@AllArgsConstructor
class RentalController {

    private final RentalFacade facade;

    @PostMapping
    void rent(@RequestBody FilmRentalRequest filmRentalRequest, Principal principal) {
        String userId = principal.getName();

        filmRentalRequest.films.forEach(
                film -> facade.rentFilm(userId, FilmDto.builder()
                        .build()));
    }

    @Getter
    @Setter
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class FilmRentalRequest {

        List<FilmRentalRequestItem> films = new ArrayList<>();

    }

    @Getter
    @Setter
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class FilmRentalRequestItem {

        String title;
        String duration;
    }
}
