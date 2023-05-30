package org.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@SpringBootApplication(scanBasePackages = {"org.gateway"})
public class Application {

    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration apiCorsConfiguration = new CorsConfiguration();
        apiCorsConfiguration.setAllowCredentials(true);
        apiCorsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        apiCorsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        apiCorsConfiguration.setAllowedMethods(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", apiCorsConfiguration);
        return source;
    }
}
