package org.example;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final String apiKey = "a938a749600845649e7134952250309";

        List<String> cities = Arrays.asList("Chisinau", "Madrid", "Kyiv", "Amsterdam");

        WeatherApiService service = WeatherClient.getClient();

        System.out.printf(
                "%-12s | %-12s | %-16s | %-16s | %-12s | %-12s%n",
                "City", "Date", "MinTemperature", "MaxTemperature", "Humidity", "Wind(kph)"
        );

        for(String city : cities) {
            Call<WeatherResponse> call = service.getWeatherForecast(apiKey, city,2);
            try {
                Response<WeatherResponse> response = call.execute();

                if(response.isSuccessful() && response.body() != null) {
                    WeatherResponse data = response.body();

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

                } else {
                    System.out.println("Error fetching data for " + city);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}