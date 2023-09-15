package org.example.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import org.example.models.WeatherForecast;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/scheduled-forecast")
public interface WeatherForecastController {

    @ApiResponses(
        @ApiResponse(responseCode = "201", content = @Content(mediaType = "application/json"))
    )
    @PostMapping(produces = "application/json")
    ResponseEntity<List<WeatherForecast>> getWeatherForecast();
}
