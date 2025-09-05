package org.example.service;

import org.example.api.WeatherApiService;
import org.example.model.weather.Day;
import org.example.model.weather.ForecastDay;
import org.example.model.weather.WeatherResponse;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    private final WeatherApiService _api;

    public WeatherService(WeatherApiService api) {
        _api = api;
    }

    public List<WeatherResponse> getForecast(String apiKey, List<String> cities) throws IOException {
        List<WeatherResponse> forecasts = new ArrayList<>();

        if(apiKey == null || apiKey.isEmpty()){
            throw new IllegalArgumentException("API key is missing.");
        }

        for(String city : cities){
            Call<WeatherResponse> call = _api.getWeatherForecast(apiKey, city, 2);

            Response<WeatherResponse> response = call.execute();

            if(response.isSuccessful() && response.body() != null){
                forecasts.add(response.body());
            } else {
                throw new IOException("Error fetching data for " + city);
            }
        }

        return forecasts;
    }

    public void printTable(List<WeatherResponse> forecasts){
        System.out.printf(
                "%-12s | %-12s | %-16s | %-16s | %-12s | %-12s | %-12s%n",
                "City", "Date", "MinTemperature", "MaxTemperature", "Humidity", "Wind(kph)", "WindDirection"
        );

        for(WeatherResponse data : forecasts){
            ForecastDay tomorrow = data.forecast.forecastDay.get(1);

            Day day = tomorrow.day;

            System.out.printf("%-12s | %-12s | %-16s | %-16s | %-12s | %-12s | %-12s%n",
                    data.location.name,
                    tomorrow.date,
                    day.minTemperature,
                    day.maxTemperature,
                    day.avgHumidity,
                    day.maxWind,
                    (day.windDirection != null ? day.windDirection : "-")
            );
        }
    }
}
