package com.course.gateway.security;

import com.course.gateway.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenFilter extends AbstractGatewayFilterFactory<JwtTokenFilter.Config> {

    private final JwtProvider jwtProvider;
    private final RouteValidator routeValidator;

    @Autowired
    public JwtTokenFilter(JwtProvider jwtProvider, RouteValidator routeValidator) {
        super(Config.class);
        this.jwtProvider = jwtProvider;
        this.routeValidator = routeValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return sendError(exchange, "Missing Authorization Header!");
                }

                String token = jwtProvider.getTokenFromRequest(exchange.getRequest());
                if (!jwtProvider.validateToken(token)) {
                    return sendError(exchange, "Invalid token!");
                }
            }

            return chain.filter(exchange);
        });
    }

    private Mono<Void> sendError(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        response.writeWith(Mono.just(response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8))));
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {

    }
}
