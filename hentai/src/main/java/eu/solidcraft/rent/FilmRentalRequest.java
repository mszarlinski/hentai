package eu.solidcraft.rent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FilmRentalRequest {
    @JsonProperty("films")
    List<FilmRentalRequestItem> films = new ArrayList<>();

    public List<FilmRentalRequestItem> getFilms() {
        return films;
    }

    public void setFilms(List<FilmRentalRequestItem> films) {
        this.films = films;
    }
}