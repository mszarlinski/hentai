package eu.solidcraft.rent;

import eu.solidcraft.shared.FilmRentalEvent;

public interface EventPublisher {
    void publish(FilmRentalEvent filmRentalEvent);
}
