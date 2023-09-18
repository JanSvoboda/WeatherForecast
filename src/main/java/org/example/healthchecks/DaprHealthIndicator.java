package org.example.healthchecks;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import lombok.NoArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DaprHealthIndicator implements HealthIndicator {

    private final DaprClient daprClient;

    public DaprHealthIndicator() {
        daprClient = new DaprClientBuilder().build();
    }
    public DaprHealthIndicator(DaprClient daprClient) {
        this.daprClient = daprClient;
    }

    @Override
    public Health health() {
        try {
            // Zde můžete provést nějaký způsob kontrolu zdraví Dapru.
            // Například, zkusit provést jednoduchý HTTP dotaz na Dapr sidecar nebo jiný vhodný způsob kontrolu.
            // Pokud je Dapr zdravý, vrátíme stav UP.
            // Pokud je Dapr nezdravý, vrátíme stav DOWN.
            // Pokud se kontrola nezdaří (např. kvůli výjimce), vrátíme stav DOWN.

            // Zde pouze simulujeme kontrolu úspěchem.
            boolean healthy = true;

            if (healthy) {
                return Health.up().withDetail("message", "Dapr sidecar is healthy.").build();
            } else {
                return Health.down().withDetail("message", "Dapr sidecar is unhealthy.").build();
            }
        } catch (Exception e) {
            return Health.down(e).withDetail("message", "Dapr sidecar check failed.").build();
        }
    }
}
