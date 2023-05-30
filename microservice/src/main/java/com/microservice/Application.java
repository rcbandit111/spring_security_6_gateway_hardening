package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
@EnableFeignClients
public class Application {

    /**
     * Initialize spring boot application.
     *
     * @param args main parameters
     */
    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
