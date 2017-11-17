package eu.solidcraft.point

import eu.solidcraft.shared.FilmTypeDto
import eu.solidcraft.shared.FilmRentalEvent
import spock.lang.Specification
import spock.lang.Unroll


class PointSpec extends Specification {

    public static final int USER1 = 1;
    public static final int USER2 = 2

    def pointFacade = new PointFacade()

    def "should tell the amount of points for an user"() {
        given:
            pointFacade.onFilmRental(new FilmRentalEvent(USER2, FilmTypeDto.OLD))
        when:
            Points points = pointFacade.getPointsForUser(USER2)
        then:
            points == new Points(1)

        when:
            points = pointFacade.getPointsForUser(USER1)
        then:

            points == Points.NO_POINTS
    }


    @Unroll
    def "should give #expectedPoints for film of type #filmType"() {
        given:
            pointFacade.onFilmRental(new FilmRentalEvent(USER2, filmType))

        when:
            def points = pointFacade.getPointsForUser(USER2)

        then:
            points == expectedPoints

        where:
            filmType            | expectedPoints
            FilmTypeDto.NEW     | new Points(2)
            FilmTypeDto.REGULAR | new Points(1)
            FilmTypeDto.OLD     | new Points(1)

    }
}
