package eu.solidcraft.rent;

import eu.solidcraft.shared.FilmDto;
import eu.solidcraft.shared.FilmRentalEvent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RentFacade {
    private final EventPublisher eventPublisher;

    public void rentFilm(String userId, FilmDto filmDto) {
        FilmRentalEvent filmRentalEvent = new FilmRentalEvent(userId, filmDto.getType());
        eventPublisher.publish(filmRentalEvent);
    }
}