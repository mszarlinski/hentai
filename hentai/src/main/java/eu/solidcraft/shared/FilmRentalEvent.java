package eu.solidcraft.shared;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class FilmRentalEvent {
    private final int userId;
    private final FilmTypeDto filmType;
}
