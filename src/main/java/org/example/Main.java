package org.example;

import org.example.api.WeatherApiService;
import org.example.client.WeatherApiClient;
import org.example.model.weather.WeatherResponse;
import org.example.service.WeatherService;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String apiKey = "";

        List<String> cities = Arrays.asList("Chisinau", "Madrid", "Kyiv", "Amsterdam");

        WeatherApiService api = WeatherApiClient.getClient();
        WeatherService service = new WeatherService(api);

        try{
            List<WeatherResponse> forecasts = service.getForecast(apiKey, cities);

            service.printTable(forecasts);
        } catch (IllegalArgumentException e) {
            System.out.println("Configuration error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Network/API error: " + e.getMessage());
        }
    }
}