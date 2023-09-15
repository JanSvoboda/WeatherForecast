package org.example.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class WeatherForecast {
    private LocalDate date;
    private int temperatureC;

    private int temperatureF;
    private String summary;

    public WeatherForecast(LocalDate date, int temperatureC, String summary) {
        this.date = date;
        this.temperatureC = temperatureC;
        this.summary = summary;
        this.temperatureF = (int)Math.round(32 + temperatureC / 0.5556);
    }
}
