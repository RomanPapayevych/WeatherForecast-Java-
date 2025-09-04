package org.example;

import org.example.model.weather.WeatherResponse;
import org.example.service.WeatherService;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String apiKey = "a938a749600845649e7134952250309";

        List<String> cities = Arrays.asList("Chisinau", "Madrid", "Kyiv", "Amsterdam");

        WeatherService service = new WeatherService();

        List<WeatherResponse> forecasts = service.getForecast(apiKey, cities);

        service.printTable(forecasts);
    }
}