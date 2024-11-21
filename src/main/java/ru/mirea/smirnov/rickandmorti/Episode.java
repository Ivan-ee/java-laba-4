package ru.mirea.smirnov.rickandmorti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Episode {
    private int id;
    private String name;
    private String air_date;
    private String episode;

    @JsonProperty("characters")
    private List<String> characters;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAirDate() {
        return air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public int countCharacters() {
        return characters.size();
    }
}
