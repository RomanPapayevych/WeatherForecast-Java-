package org.example;

import com.google.gson.annotations.SerializedName;

public class ForecastDay {
    @SerializedName("date")
    public String date;

    @SerializedName("day")
    public Day day;
}
