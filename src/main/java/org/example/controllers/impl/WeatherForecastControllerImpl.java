package org.example.controllers.impl;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.Metadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.controllers.WeatherForecastController;
import org.example.models.WeatherForecast;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.singletonMap;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherForecastControllerImpl implements WeatherForecastController {
    private static final String[] summaries = {"Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"};

    @Override
    public ResponseEntity<List<WeatherForecast>> getWeatherForecast() {
        log.info("Request received for weather forecast.");

        List<WeatherForecast> response = IntStream.range(1,5).mapToObj(index -> {
            String date = LocalDate.now().plusDays(index).toString();
            Random random = new Random();
            int temperatureC = random.nextInt(75) - 20;
            String summary = summaries[random.nextInt(summaries.length)];
            return new WeatherForecast(date, temperatureC, summary);
        }).collect(Collectors.toList());

        log.info("Weather forecast generated successfully.");

        try (DaprClient client = new DaprClientBuilder().build()) {
        //Using Dapr SDK to publish a topic
            String PUBSUB_NAME = "rabbitmq-pubsub";
            String TOPIC_NAME = "weatherforecast";
            String MESSAGE_TTL_IN_SECONDS = "1000";
            int orderId = 1;

            client.publishEvent(
                    PUBSUB_NAME,
                    TOPIC_NAME,
                    orderId,
                    singletonMap(Metadata.TTL_IN_SECONDS, MESSAGE_TTL_IN_SECONDS)).block();
            log.info("Published data:" + orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } ;

        return new ResponseEntity<>(response, CREATED);
    }
}
