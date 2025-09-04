package org.example.model.weather;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("mintemp_c")
    public double minTemperature;

    @SerializedName("maxtemp_c")
    public double maxTemperature;

    @SerializedName("avghumidity")
    public String avgHumidity;

    @SerializedName("maxwind_kph")
    public double maxWind;

    //@SerializedName("wind_dir")
    //public String windDirection;
}
