package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@Slf4j
public class AppStartupRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application WeatherForecast has started.");

        this.logSettings();
    }

    private void logSettings() {
        final List<String> systemProperties = Arrays.asList(
                "java.version"
        );

        systemProperties.stream().forEach(property -> log.info("{} is: {}", property, System.getProperty(property)));
    }
}