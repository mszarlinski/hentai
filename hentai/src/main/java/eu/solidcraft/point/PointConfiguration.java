package eu.solidcraft.point;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PointConfiguration {
    @Bean
    PointFacade pointFacade() {
        return new PointFacade();
    }
}
