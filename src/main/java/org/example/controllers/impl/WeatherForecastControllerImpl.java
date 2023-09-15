package org.example.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.example.controllers.WeatherForecastController;
import org.example.models.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class WeatherForecastControllerImpl implements WeatherForecastController {
    private static final String[] summaries = {"Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"};

    private final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    @Override
    public ResponseEntity<List<WeatherForecast>> getWeatherForecast() {
        logger.info("Request received for weather forecast.");

        List<WeatherForecast> response = IntStream.range(1,5).mapToObj(index -> {
            LocalDate date = LocalDate.now().plusDays(index)
            Random random = new Random();
            int temperatureC = random.nextInt(75) - 20;
            String summary = summaries[random.nextInt(summaries.length)];
            return new WeatherForecast(date, temperatureC, summary);
        }).collect(Collectors.toList());

        logger.info("Weather forecast generated successfully.");

        return new ResponseEntity<>(response, CREATED);
    }
}
