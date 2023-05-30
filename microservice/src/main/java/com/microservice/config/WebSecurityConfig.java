package com.microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Note: Please change '/mp-api/**' to your desired rest controller path.
        httpSecurity
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers("/**").hasRole("ROLE_TECH_SUPPORT")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2Configurer -> oauth2Configurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                    Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                    Collection<String> roles = realmAccess.get("roles");
                    var grantedAuthorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList());
                    return new JwtAuthenticationToken(jwt, grantedAuthorities);
                })));

        // State-less session (state in access-token only)
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Disable CSRF because of state-less session-management
        httpSecurity.csrf().disable();

        // Disable anonymous
        httpSecurity.anonymous().disable();

        return httpSecurity.build();
    }
}
