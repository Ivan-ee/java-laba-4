package ru.mirea.smirnov.rickandmorti;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RMService {
    @GET("/api/episode")
    Call<EpisodeResponse> getEpisodes(@Query("page") int page);
}

