package eu.solidcraft.film.dto;

import eu.solidcraft.shared.FilmTypeDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class FilmDto {
    private String title;
    private FilmTypeDto type;

}
