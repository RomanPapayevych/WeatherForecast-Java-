package org.example.service;

import org.example.api.WeatherApiService;
import org.example.client.WeatherApiClient;
import org.example.model.weather.Day;
import org.example.model.weather.ForecastDay;
import org.example.model.weather.WeatherResponse;
import retrofit2.Call;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class WeatherService {
    private final WeatherApiService api;

    public WeatherService() {
        this.api = WeatherApiClient.getClient();
    }

    public List<WeatherResponse> getForecast(String apiKey, List<String> cities){
        List<WeatherResponse> forecasts = new ArrayList<>();

        for(String city : cities){
            Call<WeatherResponse> call = api.getWeatherForecast(apiKey, city, 2);
            try{
                Response<WeatherResponse> response = call.execute();

                if(response.isSuccessful() && response.body() != null){
                    forecasts.add(response.body());
                }else{
                    System.out.println("Error fetching data for " + city);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return forecasts;
    }

    public void printTable(List<WeatherResponse> forecasts){
        System.out.printf(
                "%-12s | %-12s | %-16s | %-16s | %-12s | %-12s%n",
                "City", "Date", "MinTemperature", "MaxTemperature", "Humidity", "Wind(kph)"
        );

        for(WeatherResponse data : forecasts){
            ForecastDay tomorrow = data.forecast.forecastDay.get(1);

            Day day = tomorrow.day;

            System.out.printf("%-12s | %-12s | %-16s | %-16s | %-12s | %-12s%n",
                    data.location.name,
                    tomorrow.date,
                    day.minTemperature,
                    day.maxTemperature,
                    day.avgHumidity,
                    day.maxWind
            );
        }
    }
}
