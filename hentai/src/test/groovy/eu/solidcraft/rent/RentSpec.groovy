package eu.solidcraft.rent

import eu.solidcraft.shared.FilmDto
import eu.solidcraft.shared.FilmTypeDto
import spock.lang.Specification

import static eu.solidcraft.shared.FilmTypeDto.*

class RentSpec extends Specification {

    public static final String USER_ID = "Alice"
    EventPublisher eventPublisher = Mock(EventPublisher);
    RentFacade rentFacade = new RentFacade(eventPublisher)


    def 'when movie is rented an event is dispatched'() {
        given:
            FilmDto filmDto = new FilmDto("Dancing with wolves", OLD);

        when: 'film is rented'
            rentFacade.rentFilm(USER_ID, filmDto)

        then: 'an event is dispatched'
            1 * eventPublisher.publish({ it.filmType == OLD && it.userId == USER_ID })


    }


}
