package eu.solidcraft.film.domain

import eu.solidcraft.shared.FilmDto
import eu.solidcraft.shared.FilmTypeDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleFilms {
    FilmDto trumper = createFilmDto("50 shades of Trumpet", FilmTypeDto.NEW)
    FilmDto clingon = createFilmDto("American Clingon Bondage", FilmTypeDto.OLD)

    static private FilmDto createFilmDto(String title, FilmTypeDto type) {
        return FilmDto.builder()
                .title(title)
                .type(type)
                .build()
    }
}
