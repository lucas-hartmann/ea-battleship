package at.fh.sem4.ea.game;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;

import java.time.Duration;


@SpringBootApplication
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slowCallDurationThreshold(Duration.ofSeconds(3)) 		// Calls longer than 3 seconds are considered slow
                        .slowCallRateThreshold(50) 			                        // 50% of the calls must be slow to open the circuit breaker
                        .minimumNumberOfCalls(3) 				                // Minimum number of calls before the circuit breaker can calculate the slow call rate
                        .waitDurationInOpenState(Duration.ofSeconds(20)) 		// Circuit breaker will stay open for 20 seconds before transitioning to half-open state
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(12)) 			// Timeout duration for the TimeLimiter
                        .build())
                .build());
    }
}
