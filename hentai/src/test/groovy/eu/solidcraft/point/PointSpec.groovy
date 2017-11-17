package eu.solidcraft.point

import spock.lang.Specification


class PointSpec extends Specification {

    public static final int USER_ID = 12

    def pointFacade = new PointFacade()

    def "when I go to /points then I see I have no points"() {
        when:
            def points = pointFacade.getPointsForUser(USER_ID)

        then:
            points == 0
    }

    def "when I go to /points then I see I have 3 points"() {
        given:
            FilmRentalEvent event = new FilmRentalEvent(USER_ID);
            pointFacade.onFilmRental(event);

        when:
            def points = pointFacade.getPointsForUser(USER_ID)

        then:
            points == 3
    }
}
