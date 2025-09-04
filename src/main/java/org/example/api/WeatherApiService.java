package org.example.api;

import org.example.model.weather.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("forecast.json")
    Call<WeatherResponse> getWeatherForecast(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("days") int days
    );
}
