package com.springwebflux.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springConvertOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Vehicle usd to crypto price converter API Docs")
                        .description("REST API documentation")
                        .version("v1.0.0"));
    }
}