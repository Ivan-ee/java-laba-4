package ru.mirea.smirnov.rickandmorti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EpisodeResponse {
    @JsonProperty("info")
    private Info info;

    @JsonProperty("results")
    private List<Episode> results;

    public Info getInfo() {
        return info;
    }

    public List<Episode> getResults() {
        return results;
    }

}
