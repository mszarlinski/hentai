package eu.solidcraft.point

import eu.solidcraft.film.dto.FilmTypeDto
import spock.lang.Specification
import spock.lang.Unroll


class PointSpec extends Specification {

    public static final int USER_ID = 12

    def pointFacade = new PointFacade()

    def "when I go to /points then I see I have no points"() {
        when:
            Points points = pointFacade.getPointsForUser(USER_ID)

        then:
            points == Points.NO_POINTS
    }

//    def "when I go to /points then I see I have 3 points"() {
//        given:
//            FilmRentalEvent event = new FilmRentalEvent(USER_ID)
//            pointFacade.onFilmRental(event)
//
//        when:
//            def points = pointFacade.getPointsForUser(USER_ID)
//
//        then:
//            points == 3
//    }

    @Unroll
    def "should give #expectedPoints for film of type #filmType"() {
        given:
            pointFacade.onFilmRental(new FilmRentalEvent(USER_ID, filmType))

        when:
            def points = pointFacade.getPointsForUser(USER_ID)

        then:
            points == expectedPoints

        where:
            filmType            | expectedPoints
            FilmTypeDto.NEW     | new Points(2)
            FilmTypeDto.REGULAR | new Points(1)
            FilmTypeDto.OLD     | new Points(1)

    }
}
