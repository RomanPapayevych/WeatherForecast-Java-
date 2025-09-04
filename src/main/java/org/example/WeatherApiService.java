package org.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("forecast.json")
    Call<WeatherResponse> getWeatherForecast(
            @Query("apiKey") String apiKey,
            @Query("q") String location
    );
}
