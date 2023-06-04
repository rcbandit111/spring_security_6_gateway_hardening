package org.gateway.routes;

import org.gateway.filters.LoggingGatewayFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceRouteConfiguration {

    private TokenRelayGatewayFilterFactory filterFactory;

    @Autowired
    public ServiceRouteConfiguration(TokenRelayGatewayFilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, LoggingGatewayFilterFactory loggingFactory) {
        return builder.routes()

                // Client

                // Application form
                .route("service_summary", r -> r.path("/api/microservice/dashboard/test")
                        .filters(f -> f.rewritePath("/api/microservice/dashboard/test", "/dashboard/test")
                                .removeRequestHeader("Cookie")
                                .filter(loggingFactory.apply(new LoggingGatewayFilterFactory.Config("My Custom Message", true, true)))
                                .filter(filterFactory.apply()))
                        .uri("lb://microservice/dashboard/test"))

                .build();
    }
}
