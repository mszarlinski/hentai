package eu.solidcraft.point;

import eu.solidcraft.shared.FilmRentalEvent;
import eu.solidcraft.shared.FilmTypeDto;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class PointFacade {

    Map<String, Points> pointsMap = new ConcurrentHashMap<>();

    public Points getPointsForUser(String userId) {
        return pointsMap.getOrDefault(userId, Points.NO_POINTS);
    }

    public void onFilmRental(FilmRentalEvent event) {
        String userId = event.getUserId();
        Points points = calculatePointsForFilm(event.getFilmType());
        pointsMap.compute(userId, (u, oldVal) -> Optional.ofNullable(oldVal)
                .map(points::add)
                .orElse(points));
    }

    private Points calculatePointsForFilm(FilmTypeDto filmType) {
        return filmType == FilmTypeDto.NEW ? new Points(2) : new Points(1);
    }
}
