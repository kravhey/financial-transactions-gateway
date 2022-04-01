package io.kadmos.db.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


//https://www.notion.so/kadmos/Case-Study-Backend-Engineering-e20c314a99f1451bbdea8100c7942679

@SpringBootApplication
@ComponentScan(basePackages = "io.kadmos.controllers")
public class ServiceBSprintBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBSprintBootApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .build();
    }

}
