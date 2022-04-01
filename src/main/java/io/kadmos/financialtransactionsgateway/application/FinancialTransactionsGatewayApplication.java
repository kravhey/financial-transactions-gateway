package io.kadmos.financialtransactionsgateway.application;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;


//https://www.notion.so/kadmos/Case-Study-Backend-Engineering-e20c314a99f1451bbdea8100c7942679

@SpringBootApplication
@ComponentScan(basePackages = "io.kadmos.financialtransactionsgateway.controllers")
public class FinancialTransactionsGatewayApplication {

    @Value("${gateway.timeout}")
    private Long gatewayTimeout;

    public static void main(String[] args) {
        SpringApplication.run(FinancialTransactionsGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(p -> p.path("/savings/a/balance").filters(f -> f.circuitBreaker(c -> c.setName("a-balance")).rewritePath("/savings/a/balance", "/balance")).uri("http://localhost:8081"))
                .route(p -> p.path("/savings/b/balance").filters(f -> f.circuitBreaker(c -> c.setName("b-balance")).rewritePath("/savings/b/balance", "/balance")).uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(gatewayTimeout)).build())
                .build());
    }

}
