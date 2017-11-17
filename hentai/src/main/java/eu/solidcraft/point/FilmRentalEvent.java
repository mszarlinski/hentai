package eu.solidcraft.point;

import eu.solidcraft.film.dto.FilmTypeDto;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
class FilmRentalEvent {
    private final int userId;
    private final FilmTypeDto filmType;
}
