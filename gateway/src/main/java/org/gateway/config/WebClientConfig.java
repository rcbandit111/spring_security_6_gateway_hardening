package org.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient client() {
        return WebClient.builder()
            .build();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain (ServerHttpSecurity http) {

        http
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login(); // to redirect to oauth2 login page.

        return http.build();
    }
}
