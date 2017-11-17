package eu.solidcraft.point;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
class PointController {

    private final PointFacade facade;

    @GetMapping("/points")
    PointsResponse getPoints(Principal principal) {
        return new PointsResponse(facade.getPointsForUser(principal.getName()).getValue());
    }

    @AllArgsConstructor
    @Getter
    private static class PointsResponse {
        private final int points;
    }
}
