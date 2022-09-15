package com.abhishek.microservices.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
                .routes()
                .route(p ->
                        p.path("/currency-exchange/**")
                                .uri("lb://currency-exchange/") )
                .route(p->
                        p.path("/currency-conversion/**")
                                .uri("lb://currency-conversion/"))
                .route(p->
                        p.path("/currency-conversion-new/**")
                                .filters(
                                        f->f.rewritePath(
                                                "/currency-conversion-new",
                                                "/currency-conversion/"
                                        )
                                )
                                .uri("lb://currency-conversion/"))
                .build();
    }
}
