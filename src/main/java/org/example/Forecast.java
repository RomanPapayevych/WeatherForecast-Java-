package org.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {
    @SerializedName("forecastday")
    public List<ForecastDay> forecastDay;
}
