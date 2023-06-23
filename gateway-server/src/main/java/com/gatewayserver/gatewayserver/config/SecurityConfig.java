package com.gatewayserver.gatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.gatewayserver.gatewayserver.filters.AuthenticationFilter;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
        .route("videogames", r-> r.path("/api/videogames/**")
            .filters(f -> f.filter(filter).stripPrefix(2))
            .uri("lb://videogames"))
        .route("user-videogames", r->r.path("/api/user/**")
            .filters(f -> f.filter(filter).stripPrefix(2))
            .uri("lb://user-videogames"))
        .build();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
