package eu.solidcraft.point;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class PointFacade {

    Map<Integer, Integer> pointsMap = new ConcurrentHashMap<>();

    public Integer getPointsForUser(int userId) {
        return pointsMap.getOrDefault(userId, 0);
    }

    public void addPointsForUser(int userId, int points) {
        pointsMap.compute(userId, (u, oldVal) -> Optional.ofNullable(oldVal)
                .map(x -> x + points)
                .orElse(points));
    }
}
