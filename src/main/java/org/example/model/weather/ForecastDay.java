package org.example.model.weather;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    @SerializedName("date")
    public String date;

    @SerializedName("day")
    public Day day;
}
