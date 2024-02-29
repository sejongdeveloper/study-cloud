package com.study.gateway.filter;

import com.study.gateway.properties.TokenProperties;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    private final TokenProperties tokenProperties;

    public AuthorizationHeaderFilter(TokenProperties tokenProperties) {
        super(Config.class);
        this.tokenProperties = tokenProperties;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (isNotAuthentication(request)) {
                return createError("인증한 헤더값이 없습니다.", exchange);
            }

            if (isNotValidJwt(request)) {
                return createError("인증 토큰이 유효하지 않습니다.", exchange);
            }

            return chain.filter(exchange);
        };
    }

    private boolean isNotAuthentication(ServerHttpRequest request) {
        return !request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private boolean isNotValidJwt(ServerHttpRequest request) {
        String jws = getJws(request);

        String secret = tokenProperties.getKey();
        byte[] secretKeyBytes = Base64.getEncoder().encode(secret.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);

        String subject = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jws)
                .getPayload()
                .getSubject();

        return !StringUtils.hasText(subject);
    }

    private static String getJws(ServerHttpRequest request) {
        String authorizationHeader = request.getHeaders()
                .get(HttpHeaders.AUTHORIZATION)
                .get(0);
        return authorizationHeader
                .replace("Bearer", "")
                .trim();
    }

    private Mono<Void> createError(String errorMsg, ServerWebExchange exchange) {
        log.error(errorMsg);

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    public static class Config {}
}
