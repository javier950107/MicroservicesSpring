package com.gatewayserver.gatewayserver.filters;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalFilterSample implements GlobalFilter{

    private final Logger logger = LoggerFactory.getLogger(GlobalFilterSample.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Pre filter");

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            logger.info("Post filter");
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
        }));
    }
}

// filter factory another option
