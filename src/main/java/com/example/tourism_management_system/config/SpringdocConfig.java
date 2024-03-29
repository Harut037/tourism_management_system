package com.example.tourism_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//TODO
@OpenAPIDefinition
@Configuration
public class SpringdocConfig {

    /**
     * Create a bean that configures the base OpenAPI for API documentation.
     *
     * @return The configured OpenAPI been.
     */
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Tourism management system")
                .version("1.0.0")
                .description("Controller"));
    }
}