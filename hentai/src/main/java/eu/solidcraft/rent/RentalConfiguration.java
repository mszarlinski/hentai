package eu.solidcraft.rent;

import eu.solidcraft.shared.FilmRentalEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RentalConfiguration {

    @Bean
    EventPublisher eventPublisher() {
        return new EventPublisher() {
            @Override
            public void publish(FilmRentalEvent filmRentalEvent) {

            }
        };
    }

    @Bean
    RentalFacade rentalFacade(EventPublisher eventPublisher) {
        return new RentalFacade(eventPublisher);
    }
}
