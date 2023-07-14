package com.springwebflux.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springWebfluxOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Vehicle usd to crypto price converter API Docs")
                        .description("REST API documentation")
                        .version("v1.0.0")
                        .license(new License().name("No license").url("")))
                .externalDocs(new ExternalDocumentation()
                        .description("API Git Documentation")
                        .url("https://github.com/edGlobant/springboot-webflux-postgresql-convert"));
    }
}