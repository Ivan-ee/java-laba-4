package ru.mirea.smirnov.rickandmorti;

import com.fasterxml.jackson.databind.json.JsonMapper;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RMClient {
    public static void main(String[] args) throws IOException {
        JsonMapper jsonMapper = new JsonMapper();

        Retrofit client = new Retrofit
                .Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(JacksonConverterFactory.create(jsonMapper))
                .build();

        RMService rmService = client.create(RMService.class);

        int currentPage = 1;
        boolean hasNextPage = true;

        Episode resultEpisode = null;
        int maxCharacters = 0;

        while (hasNextPage) {
            Response<EpisodeResponse> response = rmService.getEpisodes(currentPage).execute();

            if (response.isSuccessful() && response.body() != null) {
                EpisodeResponse episodeResponse = response.body();

                for (Episode episode : episodeResponse.getResults()) {
                    int characterCount = episode.countCharacters();

                    if (characterCount > maxCharacters) {
                        maxCharacters = characterCount;
                        resultEpisode = episode;
                    }

                }

                hasNextPage = currentPage < episodeResponse.getInfo().getPages();
                currentPage++;
            } else {
                System.err.println("Ошибка: " + response.code());
                hasNextPage = false;
            }
        }

        System.out.println("ID: " + resultEpisode.getId());
        System.out.println("Name: " + resultEpisode.getName());
        System.out.println("Air Date: " + resultEpisode.getAirDate());
        System.out.println("Episode: " + resultEpisode.getEpisode());
        System.out.println("Character сount: " + maxCharacters);
        System.out.println("Characters: " + resultEpisode.getCharacters());

    }
}
